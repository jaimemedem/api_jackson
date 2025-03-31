function deleteContacto(email, row) {
  fetch(`/api/contactos/${email}`, { method: 'DELETE' })
    .then(r => {
      if (!r.ok) {
        throw new Error('Error al eliminar')
      }
      return r.text()
    })
    .then(() => {
      row.remove()
    })
    .catch(e => console.error(e))
}

function loadContactos() {
  fetch('/api/contactos')
    .then(response => response.json())
    .then(data => {
      const tbody = document.querySelector('#tablaContactos tbody')
      tbody.innerHTML = ''
      data.forEach(contacto => {
        const row = document.createElement('tr')

        const tdName = document.createElement('td')
        tdName.textContent = contacto.name

        const tdEmail = document.createElement('td')
        tdEmail.textContent = contacto.email

        const tdNumber = document.createElement('td')
        tdNumber.textContent = contacto.number

        const tdMensaje = document.createElement('td')
        tdMensaje.textContent = contacto.mensaje

        const tdAcciones = document.createElement('td')
        const btnEliminar = document.createElement('button')
        btnEliminar.textContent = 'Eliminar'
        btnEliminar.addEventListener('click', () => {
          deleteContacto(contacto.email, row)
        })
        tdAcciones.appendChild(btnEliminar)

        row.appendChild(tdName)
        row.appendChild(tdEmail)
        row.appendChild(tdNumber)
        row.appendChild(tdMensaje)
        row.appendChild(tdAcciones)
        tbody.appendChild(row)
      })
    })
    .catch(error => console.error(error))
}

document.getElementById('btnLoad').addEventListener('click', loadContactos)
