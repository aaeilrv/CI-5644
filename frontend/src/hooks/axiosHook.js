import axios from "axios";

const instanceWithoutToken = axios.create({
  baseURL: "http://localhost:3000/api/",
});

export const axiosHookWithoutToken = async (request, url, body) => {
  switch (request) {
    case "get":
      try {
        const getResponse = await instanceWithoutToken.get(url);

        return {
          status: "success",
          response: getResponse,
        };
      } catch (error) {
        return {
          status: "error",
          message: error.message,
        };
      }

    case "post":
      try {
        const postResponse = await instanceWithoutToken.post(url, body);

        return {
          status: "success",
          response: postResponse,
        };
      } catch (error) {
        return {
          status: "error",
          message: error.message,
        };
      }

    case "put":
      try {
        const putResponse = await instanceWithoutToken.put(url, body);
        return {
          status: "success",
          response: putResponse,
        };
      } catch (error) {
        return {
          status: "error",
          message: error.message,
        };
      }

    case "delete":
      try {
        const deleteResponse = await instanceWithoutToken.delete(url);
        return {
          status: "success",
          response: deleteResponse,
        };
      } catch (error) {
        return {
          status: "error",
          message: error.message,
        };
      }

    case "patch":
      try {
        const patchResponse = await instanceWithoutToken.patch(url, body);
        return {
          status: "success",
          response: patchResponse,
        };
      } catch (error) {
        console.log({ error });
        return {
          status: "error",
          message: error.message,
        };
      }

    default:
      break;
  }
};
