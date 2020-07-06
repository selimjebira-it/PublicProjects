#!/bin/bash
#Author: Sélim Jebira
# Make sure only root can run our script

if [[ $EUID -ne 0 ]] 
then
   echo "This script must be run as root"
   exit 1
fi

#method to ask user to continue
askUser(){

    read -p "do you want to execute it ? (y/n)" -n 1 answer
    echo
    if [[ $answer =~ ^[Yy]$ ]]
    then
        true
    else
        false
    fi
}
line (){
    echo -e "############################################"
}


line
echo "Installation of VSFTPD UFW and OPENSSH"


if askUser
then
    zypper rm -y vsftpd ufw
    zypper in -fy vsftpd ufw openssh
    echo "Open FireWall SSH and FTP"
    if askUser
    then
        #disable the old firewall
        systemctl disable SuSEfirewall2
        systemctl stop SuSEfirewall2
        systemctl enable ufw
        systemctl start ufw
        ufw enable
        #default rules 
        ufw default deny incoming
        ufw default allow outgoing
        #allow ftp and ssh => port 20 21 22
        ufw allow ftp
        ufw allow ssh
        ufw reload
    fi
    line
    echo "Configuration of VSFTPD"

    if askUser
    then

        echo "enable vsftpd on system startup"
        systemctl enable vsftpd
        #copy old version of vsftpd.conf
        cp /etc/vsftpd.conf /etc/vsftpd.conf.old
        echo -e "\n
        write_enable=YES\n
        dirmessage_enable=YES\n
        nopriv_user=ftpsecure\n
        ftpd_banner=Welcome to 49853 FTP service.\n
        local_enable=YES\n
        local_umask=022\n
        chroot_local_user=YES\n
        chroot_list_enable=NO\n
        local_root=/home\n
        allow_writeable_chroot=YES\n
        anonymous_enable=NO\n
        syslog_enable=YES\n
        connect_from_port_20=YES\n
        listen=YES\n
        listen_ipv6=NO\n
        ascii_upload_enable=YES\n
        ssl_enable=NO\n
        pasv_enable=YES\n
        pasv_min_port=30000\n
        pasv_max_port=30100\n
        pam_service_name=vsftpd\n" > /etc/vsftpd.conf
        #start service
        systemctl start vsftpd
        echo "creation group ftpusers"
        #create group
        groupadd ftpusers
        echo "chmod on ~ to 750"
        chmod 750 ~
    fi
    line 
    echo "Configuration of SSH"

    if askUser
    then
        echo "enable on system startup"
        systemctl enable sshd
        systemctl start sshd
    fi
fi
line
echo "create a ftp user?"

if askUser
then
    read -p "Name of the user ? " name
    cd /home
    if [[ -d $name ]]
    then
        echo "name already taken"
        exit 1
    fi
    if [ $(getent group ftpusers) ]
    then
        useradd $name
        usermod -aG ftpusers $name
        passwd $name
        mkdir /home/$name
        chown $name /home/$name
        chgrp ftpusers /home/$name
        chmod 750 /home/$name
    else
        echo "ftpusers group not present you should probably first use the script to install vsftpd"
        exit 1
    fi

fi
line 
echo "System Status of ufw ssh and ftp ?"
if askUser
then
    echo "UFW"
    systemctl status ufw | grep active
    echo "SSHD"
    systemctl status sshd | grep active
    echo "VSFTPD"
    systemctl status vsftpd | grep active
fi

echo "FIN DU SCRIPT"
line










