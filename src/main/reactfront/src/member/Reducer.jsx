// 세션 관리를 위한 액션 타입 정의
export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';

// 액션 생성자 함수
export const login = (userData) => ({
    type: LOGIN,
    payload: userData,
});

export const logout = () => ({
    type: LOGOUT,
});

// 초기 상태
const initialState = {
    isLoggedIn: false,
    userData: null,
};

// 리듀서
const Reducer = (state = initialState, action) => {
    switch (action.type) {
        case LOGIN:
            return {
                isLoggedIn: true,
                userData: action.payload,
            };
        case LOGOUT:
            return {
                isLoggedIn: false,
                userData: null,
            };
        default:
            return state;
    }
};

export default Reducer;
