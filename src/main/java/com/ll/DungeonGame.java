package com.ll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoli
 * date: 2019/2/12
 * time: 19:36
 */
public class DungeonGame {
    public static void main(String[] args) {
        DungeonGame dungeonGame = new DungeonGame();
        int[][] it = new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}};
        int[][] it1 = new int[][]{{1,-3,3},{0,-2,0},{-3,-3,-3}};//[[1,-3,3],[0,-2,0],[-3,-3,-3]]
        int[][] dd = new int[][]{{0,0}};
        System.out.println(dungeonGame.calculateMinimumHP(it));;
        System.out.println(dungeonGame.calculateMinimumHP(dd));;
        System.out.println(dungeonGame.calculateMinimumHP(it1));;
    }
    class Pair{
        int cost;
        int remain;

        public Pair(int cost, int remain) {
            this.cost = cost;
            this.remain = remain;
        }
        public Pair(Pair p){
            cost = p.cost;
            remain = p.remain;
        }

        public Pair() {

        }
    }
    class State{
        Pair history;
        Pair future;

        public State() {
            history = new Pair();
            future = new Pair();
        }
        public State(int cost){
            this();
            if(cost < 0){
                future.cost = history.cost = -cost;
                future.remain = history.remain  = 0;
            }else {
                future.cost = history.cost = 0;
                future.remain = history.remain  = cost;
            }
        }
        public State(Pair history,Pair future) {
            this.history = history;
            this.future = future;
        }
        State(State pre, int cost){
            this();
            future.remain = history.remain = pre.history.remain + cost;
            future.cost = history.cost = pre.history.cost;
            if(history.remain < 0){
                future.cost = history.cost += -history.remain;
                future.remain = history.remain = 0;
            }
        }
        public State  getNextState(State up, State left, int cost){
            State newUp = up.addCost(cost);
            State newLeft = left.addCost(cost);
            this.future.cost = this.history.cost = newUp.history.cost;
            this.future.remain = this.history.remain = newUp.history.remain;
            List<Pair>pairs =  new ArrayList<>();

            pairs.add(newUp.future);
            pairs.add(newLeft.history);
            pairs.add(newLeft.future);

            for (Pair pair : pairs) {
                if(pair.cost < this.history.cost){
                    this.history.cost= pair.cost;
                    this.history.remain = pair.remain;
                }else if(pair.cost == this.history.cost){
                    this.history.cost= pair.cost;
                    this.history.remain =Math.max(this.history.remain, pair.remain);
                }
                if(pair.remain - pair.cost > this.future.remain - this.future.cost){
                    this.future.remain = pair.remain;
                    this.future.cost = pair.cost;
                }
            }
            return this;
        }
        public State addCost(int cost){
            Pair h =new Pair(history);
            Pair f = new Pair(future);

            h.remain += cost;
            f.remain += cost;
            if(h.remain < 0){
                h.cost += -h.remain;
                h.remain = 0;
            }
            if(f.remain < 0){
                f.cost += -f.remain;
                f.remain = 0;
            }
            return  new State(h, f);
        }
    }

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        // history 为从左上角到达[i,j] 的最小花费
        // future 为剩余max(hp-cost)，即未来潜力最大值
        // 新的state由左边和上面state更新
        State[][] f = new State[row][col];
        f[0][0] = new State();
        f[0][0] = new State(dungeon[0][0]);
        int i, j;
        for (j = 1; j < col; j++){
            f[0][j]= new State(f[0][j - 1], dungeon[0][j]);
        }
        for (i = 1; i < row; i++){
            f[i][0]= new State(f[i - 1][0], dungeon[i][0]);
        }

        for (i = 1; i < row; i++){
            for (j = 1; j < col; j++){
               f[i][j] = new State().getNextState(f[i-1][j],f[i][j-1],dungeon[i][j]);
            }
        }

        return f[row - 1][col - 1].history.cost + 1;
    }
}
