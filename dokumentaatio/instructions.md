# Instructions

Start by downloading the new jar [jar file](https://github.com/kallioaa/ot-harjoitustyo/releases/download/V1/ImageChatterV1.jar)

## Starting the app

You can start the by running the following command

```
java -jar imagechatter.jar
```

## Creating an account

1. After starting the app press the create new user button
2. Please insert your username and password and press the "Crate new user"-button. Password length must be over seven characters and can not contain spaces. The app informs you if the username is already taken.
3. When a new account is created successfully you will return to the log in -screen. Now insert your account credentials and press the "Log in"-button.

## Searching for images and commenting

After logging in you should end up in a view with instructions on for searching images. For example, if we want to talk about the beautiful picture which can be found [here](https://avatars2.githubusercontent.com/u/523235?s=460&u=e62cd9bb7ce8a4acfaab9171cd137c875df7b527&v=4), we just copy the url to the comment field and open image-menu (upper left corner) and press "new image from url"-button. 

![instructions](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/searchImage.png)

After the following, you will end up in the following view.

![instructions](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/pictures/imageSearched.png)

Now you can comment the picture by typing your comment in the textfield and pressing the "send"-button. Now when someone will search for the same picture online with the app they will see your comment and image history (the values under the picture). 

The large image files are not added to the database. The images are transformed into a hashcode which does not take up more space than a regular integer. You can search the same picture from multiple different url addressed and end up in the same chat.

## Logging out and closing the app

You can log out by pressing the "Log out" button in the top right corner and you will end up in the log in-view. You can close the app at any time by closing the window. Your user session will be closed correctly even if you dont log out first.

## Configuration

The program will create a *imagechatter.db* file into the directory where you run the program from.You can reset the database by deleting the *imagechatter.db* file 
and running the app. Please shut down the app before deleting the file.
