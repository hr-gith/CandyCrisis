package models;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch {
    private PriorityQueue<Board> openList;
    private ArrayList<Board> visitedList;
    private Board root;
    private char[] solution;

    public BestFirstSearch(){
        openList = new PriorityQueue<>();
        visitedList = new ArrayList<>();
    }

    public PriorityQueue<Board> getOpenList() {
        return openList;
    }

    public ArrayList<Board> getVisitedList() {
        return visitedList;
    }

    public Board getRoot() {
        return root;
    }

    public void setRoot(Board root) {
        this.root = root;
    }

    private void setSolution() {
        //TO-DO: Find solution based on visited successors
        

        this.solution = solution;
    }

    public char[] getSolution() {
        return solution;
    }

    public void bestFirstSearch(Board root){
        this.root = root;
        boolean isGoal = false;
        openList.add(this.root);

        while(!openList.isEmpty() && !isGoal){
            Board current = openList.remove();
            visitedList.add(current);
            if (current.checkGoal()){
                isGoal = true;
            }else{
                Board[] successors = current.getSucessors();
                for(Board successor: successors){
                    if (!openList.contains(successor) && !visitedList.contains(successor)){
                        openList.add(successor);
                    }
                }
            }
        }
        this.setSolution();
    }

}
