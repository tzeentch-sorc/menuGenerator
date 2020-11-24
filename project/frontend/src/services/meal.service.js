import axios from 'axios';
const API_URL = 'http://localhost:8080/api/meals';

class MealService {
  getAll(pageNum, params){
    if(params != null && params !== '')
      return axios.get(API_URL + '/all?page=' + pageNum + '&size=15', {params});
    else return axios.get(API_URL + '/all?page=' + pageNum + '&size=15');
  }

  findByTitle(title) {
    return axios.get(API_URL + `/${title}`);
  }
}

export default new MealService();

