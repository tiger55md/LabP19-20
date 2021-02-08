/**
 * The instances of this class represent supermarkets where customers arrive at
 * queues to be served (register the goods and pay), wait their turn, and leave
 * after being served.
 * 
 * Some statistical values of interest are available at each moment.
 * 
 * @author Daniel Levandovschi fc54412
 *
 */
public class Supermarket {

	// Minimum number of queues that must be active
	private int minActivatedQueues;
	// Maximum number of customers per queue
	private int maxCustomersPerQueue;

	// The sequence of queues
	private QueueSystem<Customer> queues;

	// Elapsed time units
	private int elapsedTime;
	// Total waiting time for all customers (does not include service time)
	private int totalWaitTime;
	// Number of customers already served
	private int numCustomersServed;

	/**
	 * Constructor
	 * 
	 * @param minActivatedQueues   The minimum number of queues that must be active
	 * @param maxCustomersPerQueue The maximum number of customers per queue
	 * 
	 * @requires minActivatedQueues > 0 && maxCustomersPerQueue > 0
	 */
	public Supermarket(int minActivatedQueues, int maxCustomersPerQueue) {

		this.minActivatedQueues = minActivatedQueues;
		this.maxCustomersPerQueue = maxCustomersPerQueue;
		elapsedTime = 0;
		totalWaitTime = 0;
		numCustomersServed = 0;
		queues = new ArrayQueueSystem<>(minActivatedQueues);
	}

	/**
	 * The total number of customers in the queues of this supermarket
	 */
	public int totalNrCustomers() {
		return queues.size();
	}

	/**
	 * The number of active queues in this supermarket
	 */
	public int nrActiveQueues() {
		return queues.howManyActiveQueues();
	}

	/**
	 * The average number of customers per queue at the present moment
	 */
	public double averageCustomersPerQueue() {
		return ((double)totalNrCustomers() / (double)nrActiveQueues());
	}

	/**
	 * The average time that customers wait in queue
	 */
	public double averageWaitingTime() {
		return ((double)totalWaitTime / (double)numCustomersServed);
	}

	/**
	 * Adds a customer to an active queue: preferably to the one that has the least
	 * number of customers; if there are no queues with less than the maximum number
	 * of customers, it either activates an inactive queue, if there is one, or it
	 * creates a new queue.
	 * 
	 * @param c The customer that wants to join a queue
	 * @requires c != null
	 * @throws IllegalQueueRequest
	 */
	public void addCustomer(Customer c) throws IllegalQueueRequest {
		int tamanho = queues.focusMin();
		if(tamanho < maxCustomersPerQueue) {
			queues.enqueue(c);
		}
		else {
			if(queues.howManyQueues() == queues.howManyActiveQueues()) {
				queues.create();
				queues.activate(queues.howManyQueues() -1);
				queues.focus(queues.howManyQueues() -1);
				queues.enqueue(c);
			}
			else {
				for(int i = 0; i < queues.howManyQueues(); i++) {
					if(!queues.isActivated(i)) {
						queues.activate(i);
						queues.focus(i);
						queues.enqueue(c);
						break;
					}
				}
			}
		}
	}

	/**
	 * It processes a time unit for all activated queues: - updates the time units
	 * already elapsed; - the state of the queues: - decrements a unit of time from
	 * the timeleft of each customer that is being served (the ones that are at the
	 * first position of each queue); - if the timeleft of a client becomes zero,
	 * that client leaves the queue and the statistics (totalWaitTime,
	 * numCustomersServed) are updated
	 * 
	 * @throws IllegalQueueRequest
	 */
	public void updateActiveQueues() throws IllegalQueueRequest {
		elapsedTime++;
		int prev = queues.current(), que = 0;
		while( que < queues.howManyQueues()) {
			if(queues.isActivated(que)) {
				queues.focus(que);
				if(!queues.isEmpty()) {
					queues.front().decreaseOneTimeUnit();
					if(queues.front().timeLeft() == 0) {
						numCustomersServed++;
						totalWaitTime += elapsedTime + 1 - queues.front().arrival() - queues.front().duration();
						queues.dequeue();

					}
				}
			}
			que++;
		}
		queues.focus(prev);
	}


	/**
	 * Deactivates 1 empty queue if there are more than one active empty queue,
	 * keeping active the minimum of active queues
	 * 
	 * @throws IllegalQueueRequest
	 */
	public void updateNumberActiveQueues() throws IllegalQueueRequest {
		if(howManyActiveAndEmpty() > 1
				&& queues.howManyActiveQueues() >  minActivatedQueues) {
			for(int i = 0; i < queues.howManyQueues(); i++) {
				if(queues.isActivated(i)) {
					queues.focus(i);
					if(queues.isEmpty()) {
						queues.deactivate(i);
						break;
					}
				}
			}

		}
	}

	/**
	 * Returns the number of active and empty queues
	 * @return number of active and empty queues
	 * @throws IllegalQueueRequest
	 */
	private int howManyActiveAndEmpty() throws IllegalQueueRequest {
		int size = 0;
		for(int i = 0; i < queues.howManyQueues(); i++) {
			if(queues.isActivated(i)) {
				queues.focus(i);
				if(queues.isEmpty()) {
					size++;
				}
			}
		}
		return size;
	}

	/**
	 * Textual representation of this supermarket
	 */
	public String toString() {
		String END_LINE = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		sb.append("Minimum number of activated queues " + minActivatedQueues + END_LINE);
		sb.append("Maximum number of customers per queue " + maxCustomersPerQueue + END_LINE);
		sb.append(queues.toString());
		sb.append("Elapsed time " + elapsedTime + END_LINE);
		sb.append("Total waiting time " + totalWaitTime + END_LINE);
		sb.append("Number of customers served " + numCustomersServed + END_LINE);
		return sb.toString();
	}

	/**
	 * Writes a message to the standard output Should only be used during
	 * development of code.
	 * 
	 * @param msg The message to be written
	 */
	private void log(String msg) {
		System.out.println(msg);
	}
}
