This is a complete computer algorithm that simulates a computer server with the following specifications:
<ul>
  <li>The server consists of 2 computers.</li>
  <li>Tasks arrive at random intervals and attempt to use the available computer.</li>
  <li>If the computer is available, the task is immediately allowed to use it.</li>
  <li>Each task requires a certain amount of time (random number) and must wait for a certain random time.</li>
  <li>If the computer is currently being used, then an arriving task waits in a Queue until a computer becomes available.</li>
  <li>The task arrival time and service time follows exponential distribution. The average service time is 25 sec and the average interarrival time is 20 sec.</li>
  <li>The average waiting time for dequeued tasks is calculated at the end.</li>
  <li>Plot visualizing the distribution for both service time and inter arrival time</li>
</ul>
  
  
  
Exponential distribution formula:

PDF: f(x) = 1/M * e^(-x/M) , for M is the average

CDF: F(x) = 1 – e^(-x/M)

so in order to get the x which is the service time

Let y = F(x)

Therefore   

    1 - y = e^(- x/M)
    
    ln(1 - y) = ln(e^(- x/M))
		
    - x/M = ln(1 - y)
		
    x = - ln(1 - y) * M
    
   ln(1 - y) * M   is the same as  ln(y) * M   because it’s the symmetric opposite
 
Therefore   x = - ln(y) * M    and  y  is the random variable in [0,1)

and the M is 25 and 20 in service time and inter arrival time respectively

We also determine the max time in both to use to later

![image](https://user-images.githubusercontent.com/98273362/175291386-aca89b6f-d6dc-48c2-ad30-9a624493b57c.png)
![image](https://user-images.githubusercontent.com/98273362/175291502-1bab83eb-ab81-4801-8392-9c9fb75acfcd.png)



You can compute the code running time by summing all the passed interarrival time
![image](https://user-images.githubusercontent.com/98273362/175291693-7513f6f9-46e9-4a4b-803f-5ddb47a17598.png)




Every task generated has to be added to the queue with its service time and inter arrival time and we put this task in instance variable to use it later
![image](https://user-images.githubusercontent.com/98273362/175291871-dd4db8c4-f237-44af-bac0-205c2d101b08.png)
![image](https://user-images.githubusercontent.com/98273362/175308437-03d2069f-df1b-495b-821f-0fe9a7cf97d5.png)




Let the first time pass without decreasing the time left for the task to be finished in the each server
and to decrease the time left to subtract the arrival time of the current task from the time left in this server
and if the time left <= 0

 and the waiting queue has more than 1 task then you have to subtract the absolute value of the resulting left time from the next time left because the front element in the queue has to be dequeued abs(resultingTimeLeft) seconds ago 
but if the waiting queue has only 1 task then subtract nothing
![image](https://user-images.githubusercontent.com/98273362/175292278-9dec0dab-968b-4f8b-89b0-e4fcb4099492.png)
![image](https://user-images.githubusercontent.com/98273362/175308641-f0455273-23d8-4984-b6c9-e7fcac012fee.png)



Then check in each server whether it’s ready to receive a new task or not
![image](https://user-images.githubusercontent.com/98273362/175292428-258eb4b6-f5b2-476d-8655-7d0919bdbd11.png)
![image](https://user-images.githubusercontent.com/98273362/175292522-851af275-e169-413a-9ed4-e08aded04df5.png)



At the beginning we set the startCountingTheWaitingTasks to be the negative of numberOfComputers so we can start counting when the first numberOfComputers tasks pass and in our case it’s 2 
![image](https://user-images.githubusercontent.com/98273362/175292728-affc945a-2c81-4ce2-86ba-db6c4f88ae9f.png)




If the startCountingTheWaitingTasks >=0 then start adding the waiting time to ArrayList to sum all values later 
![image](https://user-images.githubusercontent.com/98273362/175292958-9a5836bb-6478-4ef4-be17-648a3425df83.png)




In the addToWaitingTime method we increase waiting time in each task in the queue 
![image](https://user-images.githubusercontent.com/98273362/175293259-6df56237-68fc-4849-bd84-f597b5f54386.png)
![image](https://user-images.githubusercontent.com/98273362/175293350-d5e9fe99-9483-4dc8-a990-6a8ce9a848d3.png)



Each time increase startCountingTheWaitingTasks by 1
and also count the total service time and inter arrival time to get the average 
![image](https://user-images.githubusercontent.com/98273362/175293550-b31b3dce-91ea-4b80-ab1f-d69e8e8fafc4.png)




At the end we compute the average of the service time, inter arrival time and the waiting time
![image](https://user-images.githubusercontent.com/98273362/175293669-d58bbe70-83f0-47b3-9ce0-37b02a0ff256.png)
![image](https://user-images.githubusercontent.com/98273362/175293746-926876ed-c2a0-47cb-9032-156e0c6f0306.png)



Output:
![image](https://user-images.githubusercontent.com/98273362/175293818-b0c26c21-b692-4513-8508-37246fb777be.png)



In the PlotTest class we import the Xchart library to test the distribution and we call the main() method from the MainClass to plot the data generated from it 
![image](https://user-images.githubusercontent.com/98273362/175294400-aff8d290-c920-4102-8da0-671f676cee0c.png)
![image](https://user-images.githubusercontent.com/98273362/175295695-19b13089-1e36-4e5d-b72a-792ceac61a3d.png)



We need to sort the data to traverse only one time when counting the tasks of the same service time (rounded to the nearest integer) to plot a relation between the number of tasks and time in each task

Quick Sort: time average case O(n log(n) and O(log(n)) for space worst case unlike the Merge Sort that has O(n) for space
![image](https://user-images.githubusercontent.com/98273362/175307234-b314cfb6-02a8-48eb-898f-6fef6f9904ac.png)
![image](https://user-images.githubusercontent.com/98273362/175307367-fd61990e-1214-4c84-89d0-6569020102b5.png)
![image](https://user-images.githubusercontent.com/98273362/175307461-0b390bbc-cbca-4848-bf49-80eccf536f95.png)



We count every number and skip the zero because each number has values after and before it 
Except the zero, has values just after it
So it wouldn’t be fair if we count zero and plot the data
![image](https://user-images.githubusercontent.com/98273362/175307949-e4cd31aa-f530-4f7f-ada0-f6eb5c68ddfa.png)

So definitely it the zero will count less than 1 and the plot will look like this
![image](https://user-images.githubusercontent.com/98273362/175308026-a9dd86a7-42fa-40c6-b71e-94852dd63745.png)


For this reason we have to skip the zero and this is the plot for service time 
![image](https://user-images.githubusercontent.com/98273362/175308081-45383ab3-f4ef-44e5-ba89-8ab1d2e3e0b4.png)



For inter arrival: 
![image](https://user-images.githubusercontent.com/98273362/175308124-0782b989-b7d9-48ca-be5d-f9de07d02c02.png)









