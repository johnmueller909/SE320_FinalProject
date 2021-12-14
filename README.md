# SE320_FinalProject
1. (10 points) Write a server program and a client program. The client sends the weight  and height for a person to the server. 
The server computes BMI (Body Mass Index) and  sends back to the client a string that reports the BMI. 
You can use the following formula  for computing BMI:     bmi = weightInKilograms / (heightInMeters * heightInMeters)    

2. (8 points) Revise the server program in Question-1 using threads to allow multiple  clients.    

3. (3 points) Implement the following generic method for linear search (return the first  index value if the key is found in the list, return -1 otherwise).  

public static &lt;E extends Comparable&lt;E>> 
int linearSearch(E[] list, E key)  

You can use the following code skeleton for the implementation and testing of your  code.    

public class Question3 {    public static &lt;E extends Comparable&lt;E>> int linearSearch(E[] list, E  key) {     

//INSERT YOUR IMPLEMENTATION HERE    }        

public static void main(String[] args) {      

Integer[] list = {3, 4, 5, 1, -3, -5, -1};      

System.out.println(linearSearch(list, 2));      

System.out.println(linearSearch(list, 5));     }  } 
