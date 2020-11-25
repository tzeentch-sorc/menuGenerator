<template>
  <div class="md-layout">
    <div class="md-layout-item">
        <md-field>
          <label>Поиск</label>
          <md-input v-model="filters.text"></md-input>
          <span class="md-helper-text">Найдите рецепт по душе здесь</span>
        </md-field>
    </div>
    <div class="md-layout-item">
      <md-field>
        <label for="m_type">Тип рецепта</label>
        <md-select name="m_type" id="m_type" v-model="filters.mealType" md-dense :disabled="sending">
          <md-option value="TYPE_ALL">Любой</md-option>
          <md-option value="TYPE_BREAKFAST">Завтрак</md-option>
          <md-option value="TYPE_DINNER">Обед</md-option>
          <md-option value="TYPE_SUPPER">Ужин</md-option>
          <md-option value="TYPE_DESSERT">Десерт</md-option>
          <md-option value="TYPE_ADDITIONAL">Дополнительный</md-option>
        </md-select>
      </md-field>
    </div>
    <div class="md-layout-item">
        <md-checkbox v-model="filters.searchInRecipe" class="md-primary">Искать по рецептам</md-checkbox>
    </div>
    <div class="buttons">
      <md-button class="md-primary md-raised" @click="search">Искать</md-button>
      <md-button class="md-primary md-raised" @click="randomMeal">Случайный</md-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'MealsSearch',
  data(){
    return {
      filters: {
        text:'',
        mealType: 'TYPE_ALL',
        searchInRecipe: false
      },
      sending: false
    }
  },
  methods: {
    search: function (){
      let params = new URLSearchParams({
        'filters':JSON.stringify(this.filters)
      });
      this.$emit('searchRequest', params)
    },
    randomMeal: function (){
      this.$emit('randomSearchRequest');
    }
  }
}
</script>
<style lang="scss" scoped>
.md-layout-item{
  margin: 15px;
}
.buttons{
  margin-top: 20px;
}
</style>