import axios from 'axios';
import authHeader from "@/services/auth-header";
const API_URL = 'http://localhost:5002/api/menu';

class MenuService {
    getAll(pageNum, id, params){
        if(params !== null)
            return axios.post( `${API_URL}?page=${pageNum}&sort=id,desc`,
                new URLSearchParams([["id", id], ["name", params]]),
                {headers: authHeader()});
        else return axios.post( `${API_URL}?page=${pageNum}&sort=id,desc`,
            new URLSearchParams([["id", id]]),
            {headers: authHeader()});
    }

    getCurrent(userId){
        return axios.post(`${API_URL}/current/get`, new URLSearchParams([["userId", userId]]),{headers: authHeader()});
    }

    setCurrent(id, userId){
        let params = new URLSearchParams([["id", id], ["userId",userId]])
        return axios.post(`${API_URL}/current/set`, params,
            {headers: authHeader()});
    }

    deleteById(id){
        return axios.delete(`${API_URL}/delete/${id}`,
            {headers: authHeader()});
    }
    update(params){
        return axios.post(`${API_URL}/update`, params,
            {headers: authHeader()});
    }
    getById(id){
        return axios.post(`${API_URL}/${id}`, null,{headers: authHeader()});
    }
    generate(userId){
        return axios.post(`${API_URL}/generate`, new URLSearchParams([["userId", userId]]), {headers: authHeader()})
    }
}

export default new MenuService();