
To Test the module 

docker run --rm -v /pathtoaddons/:/mnt/extra-addons --link dbcontainer:db odoo:11.0 --stop-after-init --test-enable --workers=0 -d dbtest -i moduleName

Pour Sélim

docker run --rm -v /home/selimjebira/Documents/Odoo/Addons/:/mnt/extra-addons --link db:db odoo:11.0 --stop-after-init --test-enable --workers=0 -d dbtest -i event_manager


DOC API decorator

https://www.odoo.com/documentation/13.0/reference/orm.html#module-odoo.api


DOC ODOO 11

https://www.odoo.com/documentation/11.0/howtos/backend.html#computed-fields-and-default-values

Test ODOO 11

https://www.odoo.com/documentation/11.0/reference/testing.html

Test Python

https://docs.python.org/3/library/unittest.html


OVERRIDE CREATE 

https://www.odoo.com/fr_FR/forum/aide-1/question/override-create-method-in-the-new-openerp-api-59809

https://www.slideshare.net/openobject/odoo-from-v7-to-v8-the-new-api

http://odootechnical.com/learn-overriding-create-method-in-odoo-8/


DateTime format

2020-01-17 16:18:27.72236