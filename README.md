In this program I wrote a complete computer algorithm that simulates a computer server with the following specifications:
<ul>
  <li>The server consists of 2 computers.</li>
  <li>Tasks arrive at random intervals and attempt to use the available computer.</li>
  <li>If the computer is available, the task is immediately allowed to use it.</li>
  <li>Each task requires a certain amount of time (random number) and must wait for a certain random time.</li>
  <li>If the computer is currently being used, then an arriving task waits in a Queue until a computer becomes available.</li>
  <li>The task arrival time and service time follows exponential distribution. The average service time is 25 sec and the average interarrival time is 20 sec.</li>
  <li>The average waiting time for dequeued tasks is calculated at the end.</li>
</ul>
  
