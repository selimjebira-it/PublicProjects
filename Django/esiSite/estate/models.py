from django.db import models
from django.core.validators import MaxValueValidator, MinValueValidator 

# Create your models here.
class House(models.Model):
    address = models.CharField(max_length=250,unique=True)
    price = models.PositiveIntegerField(default=1234567, validators=[MinValueValidator(1), MaxValueValidator(1000000000)])
    description=models.TextField(max_length=1024,null=True)
    def __str__(self):
        return "address:%s  price:%d  description:%s" % (self.address,self.price,self.description)


class Buyer(models.Model):
    firstName = models.CharField(max_length=255,null=False)
    lastName = models.CharField(max_length=255,null=False)
    phoneNumber = models.CharField(max_length=15,null=False)
    def __str__(self):
        return "name :%s  lastName:%s phoneNumber:%s" % (self.firstName,self.lastName,self.phoneNumber)
    
class Match(models.Model):
    house = models.ForeignKey(House,on_delete=models.DO_NOTHING)
    buyer = models.ForeignKey(Buyer,on_delete=models.DO_NOTHING)

    def __str__(self):
        return "Buyer : %s %s House: %s  Price: %d" % (self.buyer.lastName,self.buyer.firstName,self.house.address,self.house.price)

