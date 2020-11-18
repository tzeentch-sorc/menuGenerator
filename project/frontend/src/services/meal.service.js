import axios from 'axios';
const API_URL = 'http://localhost:8080/api/meals';

class MealService {
  getAll(pageNum) {
    return axios.get(API_URL + "/all?page=" + pageNum + '&size=10');
  }

  findByTitle(title) {
    return axios.get(API_URL + `/${title}`);
  }
}

export default new MealService();

