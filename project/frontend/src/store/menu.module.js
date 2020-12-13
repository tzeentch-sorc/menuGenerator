import MenuService from '@/services/menu.service';
import CurrentMenu from "@/models/currentMenu";

const currentMenu = JSON.parse(localStorage.getItem('currentMenu'));
const user = JSON.parse(localStorage.getItem('user'));
const initialProfileState = user
    ? { currentMenu }
    : { currentMenu: null };

export const currentMenuModule = {
    namespaced: true,
    state: initialProfileState,
    actions: {
        getCurrentMenu({commit}, userId) {
            return MenuService.getCurrent(userId).then(
                response => {
                    commit('currentMenuFound', response.data);
                    return Promise.resolve(response)
                },
                error => {
                    commit('currentMenuNotFound');
                    return Promise.reject(error);
                }
            )
        },
        setCurrentMenu({commit}, {newCurrentItem, userId}) {
            return MenuService.setCurrent(newCurrentItem.id, userId).then(
                response => {
                    commit('currentMenuChanged', newCurrentItem);
                    return Promise.resolve(response.data);
                }
            ).catch(
                err => {
                    commit('currentMenuNotFound');
                    return Promise.reject(err);
                }
            )
        },
        generateNew({commit}, userId) {
            return MenuService.generate(userId).then(
                response => {
                    commit('currentMenuChanged', response.data)
                    return Promise.resolve(response.data)
                }
            ).catch(
                error => {
                    commit('currentMenuNotFound');
                    return Promise.reject(error);
                }
            )
        },
        updCurrent({commit}, menu){
            commit('updateSaved', menu);
        }
    },
    mutations: {
        currentMenuFound(state, currentMenu) {
            state.currentMenu = currentMenu;
        },
        currentMenuNotFound(state){
            state.currentMenu = null;
        },
        currentMenuChanged(state, currentMenu){
            state.currentMenu = new CurrentMenu(currentMenu.id, currentMenu.name, currentMenu.description, currentMenu.mealsInMenu);
        },
        updateSaved(state, newInfo){
            state.currentMenu.id = newInfo.id;
            state.currentMenu.name = newInfo.name;
            state.currentMenu.description = newInfo.description;
        }
    }
};
