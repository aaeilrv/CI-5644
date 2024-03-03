import { getJwtToken } from "./getJwtToken";

export async function checkUserExistance() {
    const token = await getJwtToken();
    
    if (token === undefined) {
        return false;
    }

    let userRes = await fetch(
        'http://localhost:8080/v1/user',
        {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token!}`
            },
        }
    )

    return userRes.ok;
}