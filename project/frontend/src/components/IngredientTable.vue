<template>

  <md-table>
    <md-table-row>
      <md-table-head>Название</md-table-head>
      <md-table-head>Калории</md-table-head>
      <md-table-head>Протеины</md-table-head>
      <md-table-head>Жиры</md-table-head>
      <md-table-head>Углеводы</md-table-head>
      <md-table-head>Вес, г</md-table-head>
<!--      <md-table-head>Едадил</md-table-head>-->
    </md-table-row>
    <md-table-row v-for="ingr in item" v-bind:key="ingr.product.id" @click="redirect(ingr.product.name)">
      <md-table-cell>{{ingr.product.name}}</md-table-cell>
      <md-table-cell>{{ingr.product.calories}}</md-table-cell>
      <md-table-cell>{{ingr.product.proteins}}</md-table-cell>
      <md-table-cell>{{ingr.product.fats}}</md-table-cell>
      <md-table-cell>{{ingr.product.carbohydrates}}</md-table-cell>
      <md-table-cell>{{ingr.weight}}</md-table-cell>
<!--      <md-table-cell>-->
<!--        <img src="../../public/icon.png" height="35" width="35"/>-->
<!--      </md-table-cell>-->
    </md-table-row>
  </md-table>

</template>
<script>
export default {
  name: "IngredientTable",
  props:{
    item: Object
  },
  data() {
    return {

    };
  },
  mounted() {

  },
  methods:{
    redirect(name){
      window.open('https://edadeal.ru/sankt-peterburg/offers?q='+name);
    }
  }
};
</script>