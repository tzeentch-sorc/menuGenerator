<template>
  <div id="app">
    <md-toolbar class="md-transparent">
      <router-link to="/" class="nav-link">
        <img
            id="nc-logo"
            src="../public/linkedin_banner_image_1.png"
            class="logo-card"
            alt="На главную"
        />
      </router-link>
      <router-link to="/">
        <md-button>Поиск</md-button>
      </router-link>
      <router-link v-if="currentUser" to="/menu/current">
        <md-button>Текущее Меню</md-button>
      </router-link>
      <router-link v-if="showAdminBoard" to="/admin">
        <md-button>Пользователи</md-button>
      </router-link>
      <router-link v-if="showModeratorBoard" to="/mod">
        <md-button>Рецепты</md-button>
      </router-link>
      <div v-if="!currentUser" class="md-toolbar-section-end">
          <router-link to="/register" class="nav-link">
            <md-button>
              Signup
            </md-button>
          </router-link>
          <router-link to="/login" class="nav-link">
            <md-button>
              Login
            </md-button>
          </router-link>
      </div>

      <div v-if="currentUser" class="md-toolbar-section-end">
          <router-link to="/profile" class="nav-link">
            <md-button class="md-icon-button">
              <md-icon>account_circle</md-icon>
            </md-button>
          </router-link>
          <a class="nav-link" href @click.prevent="logOut">
            <md-button class="md-icon-button">
              <md-icon>exit_to_app</md-icon>
            </md-button>
          </a>
      </div>
    </md-toolbar>
    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_MODERATOR');
      }

      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.logo-card {
  width:90px;
  height: 40%;
  margin: 0 5px 5px;
  display: block;
}
</style>