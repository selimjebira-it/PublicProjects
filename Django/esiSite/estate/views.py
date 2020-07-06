from django.shortcuts import render,get_object_or_404
from django.http import HttpResponse
from .models import *
import django.views.generic

# Create your views here.


def index(request):
    buyers = Buyer.objects.all()
    return render(request, 'buyers.html', {'buyers':buyers})


def details(request,key_id):
    buyer = get_object_or_404(Buyer,pk=key_id)
    
    return render(request,'details.html',locals())