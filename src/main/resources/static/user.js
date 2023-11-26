$(async function () {
    await userU();
});

async function userU() {
    fetch(`http://localhost:8080/api/user`)
        .then(response => response.json())
        .then(usersData => {
            $(`#emailH4`).append(usersData.email);
            const rolesInHeader = usersData.roles.map(zzz => zzz.role.substring(5).concat(" ")).toString().replaceAll(`,`, ``);
            $(`#rolesUserPage`).append(rolesInHeader);
            const user =
                `$(
                <tr>
                <td>${usersData.id}</td>
                <td>${usersData.name}</td>
                <td>${usersData.lastName}</td>
                <td>${usersData.age}</td>
                <td>${usersData.email}</td>
                <td>${rolesInHeader}</td>`;
            $(`#userTable`).append(user);
        })
}