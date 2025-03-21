package com.kob.backend.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private Integer botId; // -1表示亲自出马；否则表示AI
    private String botCode;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps; // 表示每一步的方向

    private boolean check_tail_increasing(int step) { // 检验当前回合内蛇的长度是否变长
        if (step <= 10) return true;
        if (step % 3 == 1) return true;
        return false;
    }

    public List<Cell> getCells() {
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for (int d : steps) {
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            if (!check_tail_increasing(++step)) res.remove(0);
        }

        return res;
    }

    public String getStepsString() {
        StringBuilder res = new StringBuilder();
        for (int d : steps) {
            res.append(d);
        }
        return res.toString();
    }
}
