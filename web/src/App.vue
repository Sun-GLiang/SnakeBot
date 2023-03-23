<!-- html -->
<template>
  <NavBar />
  <router-view />
</template>

<!-- js -->
<script>
import NavBar from "@/components/NavBar.vue"
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from "jquery"

export default {
  components: {
    NavBar
  },
  setup() {
    // $.ajax({
    //   url: "http://localhost:3000/user/account/token/",
    //   type: "post",
    //   data: {
    //     username: "sgl",
    //     password: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // })

    // $.ajax({
    //   url: "http://localhost:3000/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmZWEyYjdiMjgyZjM0OWJhOTIwOTE5OWQwMmQ1ZTRhZiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY3OTQ4Nzg3NSwiZXhwIjoxNjgwNjk3NDc1fQ._GayCkrypuogx1xrLc52dWt1qacrLg1M4F6xZKEiyMo"
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // })

    // $.ajax({
    //   url: "http://localhost:3000/user/account/register/",
    //   type: "post",
    //   data: {
    //     username: "sgl",
    //     password: "123",
    //     confirmedPassword: "123",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     confirm.log(resp);
    //   }
    // })
  }
}
</script>

<!-- css -->
<style>
/* 设置背景图片 */
body {
  background-image: url(@/assets/images/background.png);
  background-size: cover;
}
</style>
