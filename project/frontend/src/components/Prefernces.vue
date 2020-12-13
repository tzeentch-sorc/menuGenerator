<template>
  <div>
    <md-tabs>
      <md-tab md-label="Избранное">
        <div v-masonry transition-duration="0.15s" item-selector=".item" column-width>
          <MealCard v-masonry-tile class="item" v-for="mealItem in whitelist" v-bind:key="mealItem.id" :parentData="mealItem.meal" />
        </div>
      </md-tab>
      <md-tab md-label="Черный список">
        <div v-masonry transition-duration="0.15s" item-selector=".item" column-width>
          <MealCard v-masonry-tile class="item" v-for="mealItem in blacklist" v-bind:key="mealItem.id" :parentData="mealItem.meal" />
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
  }
};
</script>
<style scoped>
</style>