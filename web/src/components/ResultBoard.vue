<template>
    <div class="result-board">
        <div class="result-board-text" v-if="$store.state.pk.loser == 'all'">
            Draw
        </div>
        <div class="result-board-text"
            v-else-if="$store.state.pk.loser == 'A' && $store.state.pk.a_id == $store.state.user.id">
            Lose
        </div>
        <div class="result-board-text"
            v-else-if="$store.state.pk.loser == 'B' && $store.state.pk.b_id == $store.state.user.id">
            Lose
        </div>
        <div class="result-board-text" v-else>
            Win
        </div>

        <div class="result-board-btn">
            <button type="button" class="btn btn-primary btn-lg" @click="restart">
                确认
            </button>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();

        const restart = () => {
            store.commit("updateStatus", "matching");
            store.commit("updateLoser", "none");
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            });
        }

        return {
            restart
        }
    }
}
</script>


<style scoped>
.result-board {
    width: 30vw;
    height: 30vh;
    background-color: rgba(50, 50, 50, 0.5);
    position: absolute;
    left: 35vw;
    top: 35vh;
}

.result-board-text {
    text-align: center;
    color: yellow;
    font-size: 60px;
    font-weight: 600;
    font-style: italic;
    padding-top: 3vh;
}

.result-board-btn {
    width: 10vw;
    text-align: center;
    margin: auto;
    padding-top: 5vh;
}
</style>