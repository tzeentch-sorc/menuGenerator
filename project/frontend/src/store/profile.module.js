import ProfileService from '../services/profile.service';

const profile = JSON.parse(localStorage.getItem('profile'));
const user = JSON.parse(localStorage.getItem('user'));
const initialProfileState = user
    ? { profile }
    : { profile: null };

export const profileModule = {
    namespaced: true,
    state: initialProfileState,
    actions: {
        getProfile({commit}, userId){
            return ProfileService.getProfile(userId).then(
                response => {
                    commit('profileFound', response.data);
                    return Promise.resolve(response)
                },
                error => {
                    commit('profileNotFound');
                    return Promise.reject(error);
                }
            )
        },
        setProfile({commit}, {newProfile, userId}){
            return ProfileService.setProfile(userId, newProfile).then(
                response => {
                    commit('profileFound', response.data)
                    return Promise.resolve(response);
                },
                error => {
                    commit('profileNotUpdated');
                    return Promise.reject(error);
                }
            )
        }
    },
    mutations: {
        profileFound(state, profile) {
            state.profile = profile;
        },
        profileNotFound(state, profile){
            state.profile = null;
        },
        profileNotUpdated(state){}
    }
};
