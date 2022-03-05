export default class HttpError extends Error{
    constructor(response) {
        super(`${response.status}`);
        this.response = response;
    }

}
