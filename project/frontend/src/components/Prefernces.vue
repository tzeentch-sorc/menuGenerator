<template>
  <div>
    <md-tabs>
      <md-tab md-label="Избранное">
        <div v-if="whitelist.length > 0" v-masonry transition-duration="0.15s" item-selector=".item" column-width>
          <MealCard v-masonry-tile class="item" v-for="mealItem in whitelist" v-bind:key="mealItem.id" :parentData="mealItem.meal" :buttonDelete="true" @itemDeleted="deleted"/>
        </div>
        <div v-else>
          <md-empty-state
              md-icon="stars"
              md-label="В избранном пусто"
              md-description="В избранном пока ничего нет. Попробуйте поискать и добавить.">
            <md-button class="md-primary md-raised" @click="goToPage(`/`)">Поиск</md-button>
          </md-empty-state>
        </div>
      </md-tab>
      <md-tab md-label="Черный список">
        <div v-if="blacklist.length > 0" v-masonry transition-duration="0.15s" item-selector=".item" column-width>
          <MealCard v-masonry-tile class="item" v-for="mealItem in blacklist" v-bind:key="mealItem.id" :parentData="mealItem.meal" :buttonDelete="true" @itemDeleted="deleted"/>
        </div>
        <div v-else>
          <md-empty-state
              md-icon="disabled_by_default"
              md-label="В черном списке пусто"
              md-description="В черном списке пока ничего нет. Попробуйте поискать и добавить.">
            <md-button class="md-primary md-raised" @click="goToPage(`/`)">Поиск</md-button>
          </md-empty-state>
        </div>
      </md-tab>
    </md-tabs>
  </div>
</template>
<script>
import PreferencesService from "@/services/preferences.service"
import MealCard from "@/components/MealCard";
export default {
  name: 'Preferences',
  components: {MealCard},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    }
  },
  data(){
    return {
      whitelist: [],
      blacklist: []
    }
  },
  mounted() {
    PreferencesService.getPref(this.currentUser.id, 0).then(
        response => {
          this.whitelist = response.data.filter(item => item.banned === false);
          this.blacklist = response.data.filter(item => item.banned === true);
        }
    ).catch( err => {alert(err)});

  },
  methods: {
    deleted(arg){
      this.whitelist = this.whitelist.filter(i => i.meal.id !== arg)
      this.blacklist = this.blacklist.filter(i => i.meal.id !== arg)
    },
    goToPage: function(page){
      this.$router.push(page);
    }
  }
};
</script>
<style scoped>
</style>