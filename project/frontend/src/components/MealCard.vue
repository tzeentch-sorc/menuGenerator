<template>
  <div>
    <md-dialog :md-active.sync="showDialog">
      <md-dialog-title>Meal title</md-dialog-title>
      <div>
        <img src="../assets/test-pic.jpeg" alt=""/>
      </div>
      <md-tabs>
        <md-tab md-label="General">
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. In non justo porttitor libero scelerisque commodo eget ac ex. Aenean blandit auctor risus, in efficitur purus venenatis a. Aenean varius finibus tortor sed dictum. Cras sed congue augue, a vehicula odio. Cras neque est, varius vitae tincidunt ut, tristique id sem. Morbi magna nulla, posuere ac libero sed, iaculis viverra quam. Vivamus finibus magna turpis, eu sagittis leo blandit iaculis. Cras rhoncus in tellus non tempor. Maecenas non cursus ligula. Proin pharetra sagittis felis. Donec molestie orci nec nisl ultrices tempor. Nullam laoreet, diam eu vehicula auctor, dolor augue interdum justo, fermentum tincidunt metus nulla sit amet massa. Mauris arcu nisl, hendrerit sed tellus vitae, dapibus vestibulum est. Nam vulputate convallis consectetur. Ut sed egestas mi.
          </p>
        </md-tab>
        <md-tab md-label="Recipe">
          <p>
            Donec eget gravida est, eu luctus ipsum. Duis vel bibendum arcu. Proin eget neque eget tellus imperdiet ultrices. Pellentesque porta laoreet fringilla. Pellentesque dignissim nulla sit amet iaculis ullamcorper. Donec nibh ipsum, sodales at interdum non, lobortis ut nisi. Etiam imperdiet purus sodales libero consequat malesuada. Cras facilisis fringilla tempor. Cras massa orci, malesuada in lectus id, laoreet egestas lacus. Praesent elementum arcu nisi, a imperdiet diam bibendum ut. Donec convallis lobortis tellus, mattis aliquet purus volutpat eu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Quisque semper velit tortor, vel convallis libero sodales ac. Duis et dictum arcu. Integer ac iaculis sem. Vestibulum id orci mollis, suscipit ipsum nec, efficitur nisi.
          </p>
        </md-tab>
      </md-tabs>
      <md-dialog-actions>
        <md-button class="md-primary" @click="showDialog=false">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
      <md-card md-with-hover @click.native="showDialog = true">
        <md-ripple>
          <md-card-media>
            <img src="../assets/test-pic.jpeg" alt="meal">
          </md-card-media>
          <md-card-header>
            <div class="md-title">Meal name</div>
          </md-card-header>
          <md-card-content>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sit amet volutpat nisi. Nulla augue lorem, congue ac molestie in, imperdiet at est. Aliquam dapibus.
          </md-card-content>
        </md-ripple>
      </md-card>
  </div>
</template>
<script>
//import MealInfo from "@/components/MealInfo";
export default {
  name: 'MealCard',
  //components: {MealInfo},
  props:{
    parentData: Object
  },
  data: () => ({
    showDialog: false
  })
}

</script>
<style lang="scss" scoped>
.md-card {
  width: 250px;
  margin: 5px 10px 15px;
  vertical-align: top;
}
</style>