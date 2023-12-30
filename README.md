## Canteen Backend API Documentation

### Register
> POST
```curl 
/user/create
```
##### body
```json
{
    "userId": 1,
    "email": "testing@example.com",
    "password": "nicepassword",
    "role": "user",
    "CreationTime":1703828679355
}
```

### UserLogin
> POST
```curl 
/user/login
```
##### body
```json
{
    "userId": 1,
    "email": "test@example.com",
    "password": "nicepassword",
    "role": "merchant",
    "CreationTime":1703828679355
}
```

### Update
> POST
```curl 
/user/update
```
##### body
```json
{
    "userId": 1,
    "email": "test@example.com",
    "password": "update_password_23",
    "role": "merchant",
    "CreationTime":1703828679355
}
```

### Create Canteen
> POST
```curl 
/canteen/create
```
##### body
```json
{
    "canteenId":1,
    "canteenName":"USST Canteen",
    "location":"jun gong road 516",
    "businessHours":"08：00-09：00",
    "description":"Usst Canteen is this best",
    "CreationTime": 1703828679355
}
```

### Canteen Update
> POST
```curl 
/canteen/update
```
##### body
```json
{
    "canteenName":"USST Canteen Updated",
    "location":"jun gong road 516 Updated",
    "businessHours":"08:00-09:00 Updated",
    "description":"Usst Canteen is this best Updated",
    "canteenId":2
}
```

### All Canteen
> GET
```curl 
/canteen/all
```
### One Canteen
> GET
```curl 
/canteen/param?id=1
```

### Dish Create
> POST
```curl 
/dish/create
```
##### body
```json
{
    "canteenId": 1,
    "dishName": "Spicy Noodles 3",
    "price": 14.50,
    "dishType": "Noodle 3",
    "CreationTime": 1703828679355
}
```

### Dish Update
> POST
```curl 
/dish/update
```
##### body
```json
{
    "dishId": 1,
    "canteenId": 1,
    "dishName": "Spicy Noodles Updated",
    "price": 13.00,
    "dishType": "Noodle Updated"
}
```

### All Dish
> GET
```curl 
/dish/all
```

### One Dish
> GET
```curl 
/dish/param?id=1
```

### Review Create
> POST
```curl 
/review/create
```
##### body
```json
{
    "userId": 1,
    "dishId": 1,
    "rating": 4,
    "comment": "Very delicious noodles with perfect spices.",
    "CreationTime": 1703828679355
}
```

### Review Update
> POST
```curl
/review/update
```
##### body
```json
{
    "reviewId": 1,
    "userId": 1,
    "dishId": 1,
    "rating": 5,
    "comment": "Updated review: Absolutely loved the noodles!"
}
```

### All Review
> GET
```curl 
/review/all
```


### One Review
> GET
```curl 
/review/param?id=4
```


