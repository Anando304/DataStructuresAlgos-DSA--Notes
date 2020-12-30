# Travel-Mapz

# Overview
- Travel-Mapz is an assignment project done as part of the Experiential Algorithmic Development course (SE 2XB3).
- The program uses a few datasets that comprise of all american cities along with the FastFood Restaurants available at these cities.
- The goal of this program is to find the optimal meal cost path while navigating from a given start to end city.
- The program can be run by executing MainActivity.java located in src\CAS2xb3_A2_zaman_AZ folder

# Route Requirements:
- You must stop at each city along the route and at each stop, you visit one restaurant.
- You do not need to visit a restaurant at your starting city, but you do need to go to a restaurant atyour destination city.
- A restaurant is considered to be within a city if it’s located within 0.5 degrees of latitude and 0.5
degrees of longitude of the city. If there are multiple restaurants within a city, any of them can be
selected.
- You cannot have the same meal in consecutive cities (e.g. You choose to stop by McDonalds to
have a Big Mac meal in the first city, you can still stop at a McDonalds in the second city, but you
cannot choose a Big Mac meal there. However, at your third city, you can once again have a Big
Mac meal).

# Algorithms Used:
- DFS & BFS for path traversal
- Dijkstras for least cost path
- QuickSort & BinarySearch
