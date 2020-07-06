from django.contrib import admin
from .models import *

# Register your models here.

@admin.register(House,Buyer,Match)
class Admin(admin.ModelAdmin):
    pass
