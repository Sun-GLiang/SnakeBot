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

export default {
  components: {
    NavBar
  }
}
</script>

<!-- css -->
<style>
body {
  background-image: url(@/assets/images/background.png);
  background-size: cover;
}
</style>
