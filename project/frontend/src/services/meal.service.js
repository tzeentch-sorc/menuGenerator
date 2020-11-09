import http from "../http-common";

class MealService {
  getAll() {
    return http.get("/meals");
  }
  get(id) {
    return http.get(`/meal/${id}`);
  }

  findByTitle(title) {
    return http.get(`/meals?title=${title}`);
  }
}

export default new MealService();

