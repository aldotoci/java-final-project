var globalNotes = ""
const editIcon = `<svg class="editIcon" xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewBox="0 0 24 24" fill="none"><path fill-rule="evenodd" clip-rule="evenodd" d="m3.99 16.854-1.314 3.504a.75.75 0 0 0 .966.965l3.503-1.314a3 3 0 0 0 1.068-.687L18.36 9.175s-.354-1.061-1.414-2.122c-1.06-1.06-2.122-1.414-2.122-1.414L4.677 15.786a3 3 0 0 0-.687 1.068zm12.249-12.63 1.383-1.383c.248-.248.579-.406.925-.348.487.08 1.232.322 1.934 1.025.703.703.945 1.447 1.025 1.934.058.346-.1.677-.348.925L19.774 7.76s-.353-1.06-1.414-2.12c-1.06-1.062-2.121-1.415-2.121-1.415z" fill="#FFF"/></svg>`
const deleteIcon = (id) => `<svg onclick="deleteNote(${id})" class="editIcon deleteIcon" xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" viewBox="0 0 1024 1024"><path fill="#D10000" d="M352 192V95.936a32 32 0 0 1 32-32h256a32 32 0 0 1 32 32V192h256a32 32 0 1 1 0 64H96a32 32 0 0 1 0-64h256zm64 0h192v-64H416v64zM192 960a32 32 0 0 1-32-32V256h704v672a32 32 0 0 1-32 32H192zm224-192a32 32 0 0 0 32-32V416a32 32 0 0 0-64 0v320a32 32 0 0 0 32 32zm192 0a32 32 0 0 0 32-32V416a32 32 0 0 0-64 0v320a32 32 0 0 0 32 32z"/></svg>`


const deleteNote = (id) => {
    fetch(`/api/delete/${id}`, {
        method: 'DELETE', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        }
    })?.then(() => {
        const index = globalNotes?.findIndex(({id: ID}) => id === ID)
        globalNotes?.splice(index, 1)
        setNotesInDOM(globalNotes)
    })
}

const saveNote = (id) => {
    const name = document.getElementById("name")?.value
    const title = document.getElementById("title")?.value
    const content = document.getElementById("content")?.value

    const data = {
        name,
        title,
        content,
        timeUnix: (new Date()).valueOf()
    }
    if(!!!id){
        fetch("/api/create", {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        window.location.href = 'http://localhost/see-notes.html';
    }else{
        fetch("/api/update", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({...data,id}),
        })?.then(res => res.json()?.then((note) => {
            const index = globalNotes?.findIndex(({id: ID}) => id === ID)
            globalNotes[index] = note
            setNotesInDOM(globalNotes)
        }))
    }
}

const openNotes = (id) => {
    console.log("id", id)
    const note = globalNotes?.find(({id: ID}) => ID === id)
    document.getElementById("body-read-note").innerHTML = `
        <div style="cursor: pointer;text-decoration: underline" onclick="setNotesInDOM(globalNotes)">go back</div>
        <input id="name" placeholder="Enter your name: " value="${note?.name}" />
        <input id="title" placeholder="Enter title: " type="text" value="${note?.title}" />
        <textarea id="content">${note?.content}</textarea>
        <input onclick="saveNote(${id})" type="submit" value="Save" />
    `
}

const setNotesInDOM = (notes) => {
    let toInsert = ""
    notes?.forEach(({name, id}) => {
        toInsert += `<div class="noteButton" onclick="openNotes(${id})" id="${id}">${name} ${editIcon}${deleteIcon(id)}</div>`
    })
    document.getElementById("body-read-note").innerHTML = toInsert
    globalNotes = notes
    console.log("notes",notes)
}

const getNotes = () => {
    fetch("/api/read").then((res) => {
        res.json().then((notes) => {
            setNotesInDOM(notes)
        })
    })
}