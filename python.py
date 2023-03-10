from django.db import models
import random
import string
# Create your models here.
def getRandomPassword():
    
    return ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(8))

def getUserName(instance):
    return instance.user.username

class Student(models.Model):


    username = models.CharField(max_length=100, unique=True, default="")
    password = models.CharField(max_length=100, unique=True, blank=False, default="")
    Name = models.CharField(max_length=100, blank=False)
    Standard = models.CharField(max_length=20, blank=False)
    Grade = models.CharField(choices=[('A', 'A'), ('B', 'B'), ('C', 'C'), ('D', 'D'), ('E', 'E')], max_length=1, blank=False)

    USERNAME_FIELD = 'username'
    is_anonymous = False
    is_authenticated = False
    REQUIRED_FIELDS = ('username', 'password',)

    def __str__(self):
        return self.Name

    class Meta:
        ordering = ['Name']


from django.contrib.auth.models import User
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework.authtoken.models import Token
from rest_framework.permissions import IsAuthenticated

from .models import Parcean
from .serializers import ParceanSerializer, UserSerializer, TokenSerializer, LoginParceanSerializer
from shops.models import Order
from shops.serializers import OrderSerializer

@api_view(['GET'])
@permission_classes((IsAuthenticated,))
def get_me(request):
    user = request.user
    parcean = Parcean.objects.get(user=user)
    serializer = ParceanSerializer(parcean)
    return Response(serializer.data)



@api_view(['POST'])
def login(request):
    serializer_class = LoginParceanSerializer
    if request.method == 'POST':
        serializer = serializer_class(data=request.data)
        if serializer.is_valid():
            username = serializer.validated_data['username']
            password = serializer.validated_data['password']
            user_query = User.objects.filter(username=username)
            if user_query.exists():
                user  = user_query.first()
                if user.check_password(password):
                    token, created = Token.objects.get_or_create(user=user)
                    return Response({'token': token.key})
                else:
                    return Response({'passwordIsNotCorrect':True}, status=status.HTTP_401_UNAUTHORIZED)
            else:
                return Response( {'message':"user do not exits", "usernameDoesNotExists":True } ,status=status.HTTP_401_UNAUTHORIZED)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        

@api_view(['POST'])
def createAccount(request):
    serializer_class = ParceanSerializer

    if request.method == 'POST':
        serializer = serializer_class(data=request.data)
        if serializer.is_valid():

            name = serializer.validated_data['name']
            username = serializer.validated_data['username']
            password = serializer.validated_data['password']
            user = User.objects.create_user(username=username, password=password)
            user.save() 
            token = Token.objects.create(user=user)
            token.save()
            parcean = Parcean(name=name, username=username, password=password, user=user)
            parcean.save()
            context = {
                'user': UserSerializer(user).data,
                'parcean': ParceanSerializer(parcean).data, 
                'token': TokenSerializer(token).data
            }

            return Response(context, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@permission_classes((IsAuthenticated,))
def get_parcean_with_orders(request):
    user = request.user
    parcean = Parcean.objects.get(user=user)
    orders = Order.objects.filter(parcean=parcean)
    orders_dict = []
    for order in orders:
        orders_dict.append({
            'item': order.item.name,
            'parcean': order.parcean.name,
            'quantity': order.quantity,
            'total': order.total,
            'id': order.id
        })
    return Response({'parcean': ParceanSerializer(parcean).data, 'orders': orders_dict})