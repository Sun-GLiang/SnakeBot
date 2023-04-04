<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;">
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-header">
                        <span style="font-size: 150%;">
                            我的Bot
                        </span>
                        <button type="button" class="btn btn-secondary float-end" data-bs-toggle="modal"
                            data-bs-target="#add-bot-btn">
                            创建Bot
                        </button>


                        <!-- Modal -->
                        <div class="modal fade" id="add-bot-btn" tabindex="-1">
                            <div class="modal-dialog modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">创建Bot</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add-bot-title" class="form-label">Bot名称</label>
                                            <input v-model="addBot.title" type="text" class="form-control"
                                                id="add-bot-title" placeholder="请输入您的Bot名称">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-description" class="form-label">Bot简介</label>
                                            <textarea v-model="addBot.description" class="form-control"
                                                id="add-bot-description" rows="3" placeholder="请输入您的Bot简介"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-bot-code" class="form-label">Bot代码</label>
                                            <VAceEditor v-model:value="addBot.content" @init="editorInit" lang="c_cpp"
                                                theme="textmate" style="height: 300px" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ addBot.error_message }}</div>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" @click="botAdd">创建</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Bot名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{ bot.title }}</td>
                                    <td>{{ bot.createtime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-light" style="margin-right: 10px;"
                                            data-bs-toggle="modal"
                                            :data-bs-target="'#update-bot-modal-' + bot.id">修改</button>
                                        <button type="button" class="btn btn-danger" @click="removeBot(bot)">删除</button>

                                        <!-- Modal -->
                                        <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5">创建Bot</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="add-bot-title" class="form-label">Bot名称</label>
                                                            <input v-model="bot.title" type="text" class="form-control"
                                                                id="add-bot-title">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add-bot-description"
                                                                class="form-label">Bot简介</label>
                                                            <textarea v-model="bot.description" class="form-control"
                                                                id="add-bot-description" rows="3"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add-bot-code" class="form-label">Bot代码</label>
                                                            <VAceEditor v-model:value="bot.content" @init="editorInit"
                                                                lang="c_cpp" theme="textmate" style="height: 300px" />
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ bot.error_message }}</div>
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">取消</button>
                                                        <button type="button" class="btn btn-primary"
                                                            @click="updateBot(bot)">修改</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive } from 'vue';
import $ from 'jquery';
import { useStore } from 'vuex';
import { Modal } from 'bootstrap/dist/js/bootstrap';
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

export default {
    components: {
        VAceEditor
    },

    setup() {
        ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/"
        )

        const store = useStore();
        let bots = ref([]);

        const addBot = reactive({
            title: "",
            description: "",
            content: "",
            error_message: "",
        });

        const refresh_bot = () => {
            $.ajax({
                url: "http://localhost:3000/user/bot/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    bots.value = resp;
                }
            })
        }

        refresh_bot();

        const botAdd = () => {
            addBot.error_message = "";
            $.ajax({
                url: "http://localhost:3000/user/bot/add/",
                type: "post",
                data: {
                    title: addBot.title,
                    description: addBot.description,
                    content: addBot.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        addBot.title = "";
                        addBot.description = "";
                        addBot.content = "";
                        Modal.getInstance("#add-bot-btn").hide();
                        refresh_bot();
                    } else {
                        addBot.error_message = resp.error_message;
                    }
                },
            })
        }

        const updateBot = (bot) => {
            $.ajax({
                url: "http://localhost:3000/user/bot/update/",
                type: "post",
                data: {
                    bot_id: bot.id,
                    title: bot.title,
                    description: bot.description,
                    content: bot.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        Modal.getInstance('#update-bot-modal-' + bot.id).hide();
                        refresh_bot();
                    } else {
                        bot.error_message = resp.error_message;
                    }
                }
            })
        }

        const removeBot = (bot) => {
            $.ajax({
                url: "http://localhost:3000/user/bot/remove/",
                type: "post",
                data: {
                    bot_id: bot.id
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    console.log(resp);
                    if (resp.error_message === "success") {
                        refresh_bot();
                    }
                }
            });
        }

        return {
            bots,
            addBot,
            botAdd,
            removeBot,
            updateBot,
        }
    }
}

</script>

<style scoped>
button {
    width: 25%
}

div.error-message {
    color: red
}
</style>