<template>
  <md-table>
    <md-table-row>
      <md-table-cell>Белки</md-table-cell>
      <md-table-cell>{{mealItem.proteins}}</md-table-cell>
    </md-table-row>
    <md-table-row>
      <md-table-cell>Жиры</md-table-cell>
      <md-table-cell>{{mealItem.fats}}</md-table-cell>
    </md-table-row>
    <md-table-row>
      <md-table-cell>Углеводы</md-table-cell>
      <md-table-cell>{{mealItem.carbohydrates}}</md-table-cell>
    </md-table-row>
  </md-table>
</template>
<script>

export default {
  name: 'MealStatsTable',
  props: {
    mealItem: Object
  }
}
</script>