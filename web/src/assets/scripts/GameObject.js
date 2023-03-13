const GAME_OBJECTS = []; // 存储所有游戏物体的基类

export class GameObject {
    constructor() {
        GAME_OBJECTS.push(this);
        this.timeDelta = 0;
        this.hasCalledStart = false;
    }

    start() { // 只执行一次

    }

    update() { // 每一帧执行一次，（除了第一帧）

    }

    on_destroy() { // 删除之前执行

    }

    destroy() {
        this.on_destroy();

        for (let i in GAME_OBJECTS) {
            const obj = GAME_OBJECTS[i];
            if (obj === this) {
                GAME_OBJECTS.splice(i);
                break;
            }
        }
    }
}


let lastTimeStamp; // 上一次执行的时刻
const step = timeStamp => { // 传入参数-这一次执行的时刻
    for (let obj of GAME_OBJECTS) {
        if (!obj.hasCalledStart) {
            obj.start();
            obj.hasCalledStart = true;
        } else {
            obj.timeDelta = timeStamp - lastTimeStamp;
            obj.update();
        }
    }

    lastTimeStamp = timeStamp;
    requestAnimationFrame(step)
}

requestAnimationFrame(step)
