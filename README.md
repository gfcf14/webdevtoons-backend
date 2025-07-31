# WebDevToons BackEnd

This repository is the backend part of my WebDevToons project. You can find the frontend part [here](https://github.com/gfcf14/webdevtoons-frontend).

## Description
The WebDevToons backend project is a web service which receives communications from a frontend application [here](https://webdevtoons.vercel.app/) to fetch records from a remote database to display the WebDevToons webcomic posts sorted by date, or a specific record from a specific page as noted by the post date. The project also features basic authentication, where only an authorized user (me) would be able to access to create new records, i.e. every time I post a new webcomic post online.

## Purpose
I created these repositories to leverage the hosting of my WebDevToons webcomic from different social media sites, where I regularly post these images but these are mixed with other works. As such, I wanted to have a space where I could only show the webcomic's posts. 

## Basic Data Flow
![enter image description here](https://github.com/gfcf14/webdevtoons-frontend/blob/main/public/assets/images/webdevtoons-simple-flow.jpg?raw=true)

This application consists of a linear, layered architecture with a simple frontend application developed in Angular, which communicates with a backend server in Spring Boot, which in turn communicates with a remote PostgreSQL database hosted in Supabase.

## Complete Data Flow
![enter image description here](https://github.com/gfcf14/webdevtoons-frontend/blob/main/public/assets/images/webdevtoons-complete-flow.jpg?raw=true)
  
The complete data flow in the backend involves a basic configuration which blocks all incoming communications except those that are allowed as per CORS configuration. Successful communication beyond this is handled by two controllers. The post controller handles communications with the post service to either get posts (all or a single one) or create a post. This service in turn communicates with the post repository which extends a JPA repository to handle basic operations (like save), as well as more complex ones with custom queries, e.g. if the user attempts to enter a date for a post which doesn't exist, instead of simply routing them to a 404 page they are given the closest post date available, or the first post date (except when the user enters a date for a post in the future, which implicitly would not exist).

While this could be all that's needed to display the webcomic posts, I also intended for this project to serve as a means for me to easily add new posts as I create and post them in my social media sites. It's for this purpose that this application uses an auth controller, which handles login attempts in the `/create` page. The gist of its logic involves checking credentials in the payload to a BCrypt encrypted password in a `users` table in the database, should the users match. If successful, the backend will send back a token for the frontend application to store, and use it within the next 15 minutes to create a post.

Please note this is the explanation of data flow for the backend part. If you wish to read on the backend part, please go [here](https://github.com/gfcf14/webdevtoons-frontend).

## Considerations
Since this is a personal project, I decided to develop and host my apps for free. As such, the frontend application is hosted in Vercel's free tier, the backend is hosted via Render's free tier, and the database is hosted using Supabase's free tier. However, though each of these deployments is successful, sometimes I have experienced delays in operations due to cold start times (particularly using Render's free plan). Considering how this diminishes user experience, I have decided on an alternative approach that is virtually cold-start free, but instead utilizes an extendable [shared backend architecture](https://github.com/gfcf14/gfcf14-sba) also hosted in Vercel to leverage their minimal cold start time.