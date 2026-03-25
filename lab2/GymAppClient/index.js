const API_URL = 'https://localhost:7196/api/memberships';

document.addEventListener('DOMContentLoaded', loadMemberships);

async function loadMemberships() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Помилка мережі');
        
        const data = await response.json();
        const tbody = document.getElementById('tableBody');
        tbody.innerHTML = '';

        data.forEach(m => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${m.id}</td>
                <td>${m.membershipType}</td>
                <td>${m.price} грн</td>
                <td>${m.gymId}</td>
                <td>${m.visitorId}</td>
                <td>
                    <button class="edit-btn" onclick="window.location.href='details.html?id=${m.id}'">Редагувати</button>
                    <button class="delete-btn" onclick="deleteMembership(${m.id})">Видалити</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Помилка:', error);
        alert('Не вдалося завантажити дані. Перевірте, чи працює API.');
    }
}

async function deleteMembership(id) {
    if (!confirm('Ви впевнені, що хочете видалити цей абонемент?')) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (response.ok) {
            loadMemberships();
        } else {
            alert('Помилка при видаленні');
        }
    } catch (error) {
        console.error('Помилка:', error);
    }
}