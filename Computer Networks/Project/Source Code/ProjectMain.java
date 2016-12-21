import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Sachin K
 *
 */

public class ProjectMain {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Scanner on = new Scanner(System.in);
		String op;
		int source = 0, dest;

		
		System.out.println("Input original network topology matix data file: ");
		String fileName = in.nextLine();
		// reading filename containing topology matrix

		List<List<Integer>> matrix;
		List<Integer> removedNodes = new ArrayList<Integer>();

		matrix = readFileIntoMatrix(fileName);
		
		//Check if file is valid with topology matrix
		if (matrix == null || matrix.size() == 0) { 
			if (matrix != null) {
				System.out.println("File Deosnt contain Topalogy Matrix");
			}
			System.out.println("\nExit CS542 project.  Good Bye! ");
			return;
		}
		// to check if input file topology matrix deosnt have any error
		boolean isFileErrorfree = true;
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.size(); j++) {
				// check if there are errors n topology
				if (matrix.get(i).get(j) != matrix.get(j).get(i) && 
						(matrix.get(i).get(j)>= 0 || matrix.get(j).get(i)>=0)) {
					if (isFileErrorfree) {
						System.out.println("Input file error");
					}
					System.out.println("Cost(" + (i + 1) + "," + (j + 1)
							+ ") != Cost(" + (j + 1) + "," + (i + 1) + ")");
					isFileErrorfree = false;
				}
				if ( i==j && matrix.get(i).get(j)!= 0) {
					if (isFileErrorfree) {
						System.out.println("Input file error");
					}
					System.out.println("Cost(" + (i + 1) + "," + (j + 1)
							+ ") != 0 ");
					isFileErrorfree = false;
				}
			}
		}
		
		if (!isFileErrorfree) {
			System.out.println("\nExit CS542 project.  Good Bye! ");
			return;
		}

		int prevOption = 0;

		while (true) {

			List<Object[]> resultList = extractShortestPath(matrix,
					removedNodes);

			System.out
					.println("\n CS542 Link State Routing Simulator  : \n"
							+ "(1) Create a Network Topology  \n"
							+ "(2) Build a Connection Table for all nodes  \n"
							+ "(3) Build a Connection Table for given source node  \n"
							+ "(4) Shortest Path to Destination Router [choose only if last option was 3]  \n"
							+ "(5) Modify a topology  \n" + "(6) Exit \n"
							+ "\n Command : ");

			int choice;

			//Making sure that source node is selected before destination node
			while (true) {
				choice = on.nextInt();
				if (choice == 4 && prevOption != 3) {
					System.out
							.println("you have to choose choice 3 before choice 4");
					continue;
				} else {
					break;
				}
			}

			switch (choice) {
			case 1:
				System.out.println("Review topology matrix: ");
				printTopologyMatrix(matrix);
				break;

			case 2:
				System.out.println("Review Connection table: ");
				printConnectionTable(resultList, removedNodes);
				break;

			case 3:

				System.out.println("Enter Source Node:");
				for (;;) {
					source = on.nextInt(); //read source node
					//make sure source node is within the network and is not removed
					if (removedNodes.contains(source) || source > matrix.size()) {
						System.out.println(" node dosent exist in network\n "
								+ "Enter new source node");
					} else {
						break;
					}
				}
				performSourceNodeOperations(source, resultList);
				break;

			case 4:
				System.out.println("Enter Destination Node");
				for (;;) {
					dest = on.nextInt(); //read destination node
					//make sure destination node is within the network and is not removed
					if (removedNodes.contains(dest) || dest > matrix.size()) {
						System.out.println(" node dosent exist in network\n "
								+ "Enter new Destination node");
					} else {
						break;
					}
				}
				performDestinationNodeOperations(source, dest, resultList);
				break;

			case 5:
				System.out.println("Enter Node to remove");
				int node;

				for (;;) {
					node = on.nextInt(); //read node to remove
					// make sure node is within network
					if (node > matrix.size()) {
						System.out.println("Invalid node\nenter another node");
						continue;
					} else if (removedNodes.contains(node)) { // make sure node is not removed
						System.out.println("Node already removed\n");
						continue;
					} else {
						break;
					}
				}
				List<List<Integer>> tempMatrix = performRemoveNode(matrix, node);
				System.out.println(" Node"+node+" removed from network");
				//assign tempmatrix with node removed to original matrix
				matrix = tempMatrix;
				//removed nodes list is maintained for further reference
				removedNodes.add(node);
				break;

			case 6:
				break;

			default:
				System.out.println("Invalid option\n");
				break;
			}

			if (choice == 6) {
				break;
			} else if (removedNodes.size() == matrix.size() - 1) {// exit if matrix has only one node left in network
				System.out.println("Review topology matrix: ");
				printTopologyMatrix(matrix);
				System.out.println("\n only 1 node in network");
				System.out.println("No more operations possible");
				break;
			}
			prevOption = choice;

		}

		System.out.println("\nExit CS542 project.  Good Bye! ");

	}
/**
 * The method takes topology matrix read from file as input and displays it
 * @param matrix
 */
	private static void printTopologyMatrix(List<List<Integer>> matrix) {
		
		System.out.print("   | ");
		for (int j = 1; j <= matrix.size(); j++) {
			if(j < 10){
				System.out.print(" ");
			}
			System.out.print(j + " ");
		}
		System.out.println("\n-------------------------------------");
		int rowCount = 1;
		//print topology matrix
		for (List<Integer> rows : matrix) {
			if(rowCount < 10){
				System.out.print(" ");
			}
			System.out.print(rowCount + " | ");
			rowCount++;
			for (Integer val : rows) {
				// condition to align display
				if(-1 < val && 10 > val)
					System.out.print(" ");
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Takes the file name as the input and reads file into a matrix and returns the matrix
	 * @param fileName
	 * @return
	 */
	private static List<List<Integer>> readFileIntoMatrix(String fileName) {
		
		List<List<Integer>> matrix = null;
		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			matrix = new ArrayList<List<Integer>>();
			String curLineString = br.readLine(); // reading first line of the file

			while (null != curLineString) {

				// Splitting curLineString by space into array of distances
				String[] distanceArray = curLineString.split(" ");

				List<Integer> row = new ArrayList<>();
				for (String value : distanceArray) {
					row.add(Integer.valueOf(value)); // reading values into row list
				}
				matrix.add(row); // reading rows into matrix
				curLineString = br.readLine();// reading second to last line of file
			}
			br.close();// close reader

		} catch (FileNotFoundException e) {
			//if file is invalid execption is given
			System.out.println("File not found");
		} catch (IOException e) {
			//if file cannot be read execption is given
			System.out.println("Cannot read file");
		}catch (NumberFormatException e){
			//if file contents are string invalid execption is given
			System.out.println("File contents invalid");
		}

		return matrix;
	}

	/**
	 * It takes list of <object[]> result list as input along with the list of removed nodes.
	 * Prints topology matrix for nodes that are not in removed nodes.
	 * @param resultList
	 * @param removedNodes
	 */
	private static void printConnectionTable(List<Object[]> resultList,
			List<Integer> removedNodes) {
		for (int i = 0; i < resultList.size(); i++) {
			if (removedNodes.contains(i + 1)) {
				//connection table is not displayed for removed node
				System.out.println("Router" + (i + 1) + " is removed");
				continue;
			}
			Object[] resultObj = resultList.get(i);
			System.out.println("\nRouter" + (i + 1) + " connection table");
			System.out.println("================================");
			System.out.println("Destination      Interface");
			//Read path for nnodei+1
			String[] path = (String[]) resultObj[1];
			for (int j = 1; j <= path.length; j++) {
				String interfac;
				//display second node in the path as interface
				if (path[j - 1].equalsIgnoreCase("-")) {
					interfac = path[j - 1];
				}else if (path[j - 1].length() == 3) {
					interfac = path[j - 1].substring(2, 3);
				}else if (path[j - 1].length() == 4) {
					interfac = path[j - 1].substring(2, 4);
				} else if (i < 10) {
					interfac = path[j - 1].substring(2, 4);
				} else {
					interfac = path[j - 1].substring(3, 5);
				}
				
				if(interfac.length()>1 && interfac.substring(1, 2).equals(",")){
					interfac = interfac.substring(0, 1);
				} else if(interfac.length()>1 && interfac.substring(0, 1).equals(",")){
					interfac = interfac.substring(1, 2);
				}
				
				System.out.println("  " + j + "           " + "     "
						+ interfac);
			}

		}
	}

	/**
	 * This method takes List<Object[]> resultList and source as input. 
	 * For each node it displays the node,interface from the source, path from source to node.
	 * @param source
	 * @param resultList
	 */
	private static void performSourceNodeOperations(int source,
			List<Object[]> resultList) {
		Object[] resultObj = resultList.get(source - 1);
		//read path from object
		String[] path = (String[]) resultObj[1];
		System.out.println("Router   Interface   Path");
		for (int i = 1; i <= path.length; i++) {
			String interfac;
			//display second node in the path as interface
			if (path[i - 1].equalsIgnoreCase("-")) {
				interfac = path[i - 1];
			} else if (path[i - 1].length() == 3) {
				interfac = path[i - 1].substring(2, 3);
			} else if (path[i - 1].length() == 4) {
				interfac = path[i - 1].substring(2, 4);
			} else if (source < 10) {
				interfac = path[i - 1].substring(2, 4);
			} else {
				interfac = path[i - 1].substring(3, 5);
			}
			
			if(interfac.length()>1 && interfac.substring(1, 2).equals(",")){
				interfac = interfac.substring(0, 1);
			} else if(interfac.length()>1 && interfac.substring(0, 1).equals(",")){
				interfac = interfac.substring(1, 2);
			}
			
			System.out.println(i + "         " + interfac + "          "
					+ path[i - 1].replaceAll(",", "-->"));
		}
	}

	/**
	 * This method displays distance and path between a given source and destination nodes.
	 * @param source
	 * @param dest
	 * @param resultList
	 */
	private static void performDestinationNodeOperations(int source, int dest,
			List<Object[]> resultList) {
		Object[] resultObj1 = resultList.get(source - 1);
		//read distance from object
		int[] distance = (int[]) resultObj1[0];
		//read path from object
		String[] path1 = (String[]) resultObj1[1];
		//display distance and path
		System.out.println("Router" + source + " to " + "Router" + dest);
		System.out.println("Cost  = " + distance[dest - 1]);
		System.out.println("Path  = "
				+ path1[dest - 1].replaceAll(",", "-->"));
	}

	/**
	 * This method creates a temporary matrix and and copies matrix value to it.
	 * For removed nodes -1 is assigned as cost and returns the temporary matrix.
	 * @param matrix
	 * @param node
	 * @return
	 */
	private static List<List<Integer>> performRemoveNode(
			List<List<Integer>> matrix, int node) {
		List<List<Integer>> tempMatrix = new ArrayList<List<Integer>>();
		//create temp matrisx
		for (int i = 0; i < matrix.size(); i++) {
			List<Integer> templist = new ArrayList<Integer>();
			for (int j = 0; j < matrix.size(); j++) {
				if (i == node - 1 || j == node - 1) {
					//assign cost of node to be removed as -1
					templist.add(-1);
				} else {
					//assign cost of not removed node as that of original matrix
					templist.add(matrix.get(i).get(j));
				}
			}
			tempMatrix.add(templist);
		}
		//return tempmatrix with node removed
		return tempMatrix;
	}

	/**
	 * It performs the dijkstars algorithm and finds the shortest distance from source to all other nodes.
	 * It also finds path.Combines both distance and path array and returns as an object.
	 * @param arr
	 * @param size
	 * @param source
	 * @param maxVal
	 * @param removedNodes
	 * @return
	 */
	// dijkstras algorithm used
	private static Object[] findShortestPathForGivenSource(int[][] arr,
			int size, int source, int maxVal, List<Integer> removedNodes) {

		boolean[] visited = new boolean[size];

		int[] distance = new int[size];
		String[] path = new String[size];
		int min;
		int nextNode = 0;
		String prevPath;
		source--;

		for (int i = 0; i < size; i++) {
			visited[i] = false; // vsited nodes are set to false
			distance[i] = arr[source][i]; //distance array is source row of array
		}
		distance[source] = 0;

		for (int i = 0; i < size; i++) {
			//set inital values for path
			path[i] = String.valueOf(source + 1) + "," + String.valueOf(i + 1);
			if (arr[i][source] == maxVal) {
				path[i] = String.valueOf(source + 1);
			}
		}
		path[source] = "-";
		//visitid of source is set to false and it is not visited 
		visited[source] = true;

		for (int i = 0; i < size; i++) {
			//visitid of removed nodes are set to false and are not visited 
			if (removedNodes.contains(i + 1)) {
				path[i] = "-";
				visited[i] = true;
			}
		}

		for (int i = 0; i < size; i++) {
			min = maxVal;

			for (int j = 0; j < size; j++)
				// for all unvisited nodes find minimum distance with src 
				// and the node is noted as nextNode
				if (min > distance[j] && visited[j] != true) {
					min = distance[j];
					nextNode = j;
				}

			visited[nextNode] = true; //This node is set to visited =  true
			prevPath = path[nextNode]; //prevPath is the path of the nod with min dist

			for (int k = 0; k < size; k++) {
				if (visited[k] != true) {
					//for each unvisited node k 
					//if minimum dist + cost(nextNode,k) < current distance of k with source
					if (min + arr[nextNode][k] < distance[k]) {
						//distance is minvalue
						//path is minvalue node path,node k
						distance[k] = min + arr[nextNode][k];
						path[k] = prevPath + "," + String.valueOf(k + 1);
					}
				}
			}
		}
		Object[] obj = new Object[2];
		obj[0] = distance;
		obj[1] = path;
		// return distance and path array as object
		return obj;
	}

	/**
	 * For a given matrix, it creates an array with cost-1 replaced with maximum value.
	 * Calls findshortestpath for given source() to find shortest path and distance.
	 * @param matrix
	 * @param removedNodes
	 * @return
	 */
	private static List<Object[]> extractShortestPath(
			List<List<Integer>> matrix, List<Integer> removedNodes) {
		List<Object[]> resultList = new ArrayList<Object[]>();
		int size = matrix.size();
		int[][] arr = new int[size][size];
		int maxVal = 999;

		Scanner in = new Scanner(System.in);
		// converting list matrix to two Dimension array
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				arr[i][j] = matrix.get(i).get(j);
				// When no connection, assigning high value to ignore
				if (arr[i][j] <= -1)
					arr[i][j] = maxVal;
			}
		}
		for (int i = 0; i < matrix.size(); i++) {
			Object[] resultObj = findShortestPathForGivenSource(arr, size,
					i + 1, maxVal, removedNodes);
			resultList.add(resultObj);
		}
		return resultList;
	}


	}

