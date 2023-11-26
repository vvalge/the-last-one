$(async function() {
    await allUsers();
});
const table = $('#tbodyAllUserTable');
async function allUsers() {
    table.empty()
    fetch(`http://localhost:8080/api/admin`)
        .then(response => response.json())
        .then(usersData => {
            $(`#emailH4`).append(usersData.email);

            usersData.forEach(shownUser => {
                const roles = shownUser.roles.map(zzz => zzz.role.substring(5).concat(" ")).toString().replaceAll(`,`, ``);

                const usersTable = `$(
                        <tr>
                            <td>${shownUser.id}</td>
                            <td>${shownUser.name}</td>   
                            <td>${shownUser.lastname}</td> 
                            <td>${shownUser.age}</td>                      
                            <td>${shownUser.email}</td>
                            <td>${roles}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                                data-action="edit" data-id="${shownUser.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${shownUser.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                table.append(usersTable);

            })

        })
}