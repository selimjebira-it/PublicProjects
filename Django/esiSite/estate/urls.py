
from django.urls import path
from .views import *

urlpatterns = [
    path('',index),
    path('buyer/<int:key_id>',details),
]


