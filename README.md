# HttpNotesApiKtor [![](https://skillicons.dev/icons?i=kotlin&theme=dark)](https://skillicons.dev)

<a href="https://www.linkedin.com/in/adel-ayman-2497ab1b3/">
    <img src="https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn Badge"/>
  </a>
 
Notes API That's Built With Ktor to keep and organize user's notes.
This repository contains a **Notes Api** This is an educational API.Use and run this API to learn more about API design and best practices
you must follow.That's built with Ktor to keep  and organize users's notes , That's implements Pagination,Coroutines,JWT,clean architecture,Koin,Exposed,jackson,etc... using kotlin language with ktor framework this is server side you can find usage of this API in real project here -> [AndroidNoteApp](https://github.com/adelayman1/)
You can find postman document here -> [UserEndpoint](https://documenter.getpostman.com/view/14561772/2s8YzP3Qe4)  [NoteEndpoint](https://documenter.getpostman.com/view/14561772/2s8YzP3QZn) 

![](https://user-images.githubusercontent.com/85571327/206309085-d497f390-4670-4b65-8c76-d4f078ae16f8.png)

## Table Of Content
- [Endpoints](#endpoints)
  - [User endpoint](#user-endpoint)
    - [Introduction](#introduction)
    - [Operatores](#operators)
      - [Login](#post-login)
      - [Register](#post-register)
  - [Notes endpoint](#notes-endpoint)
    - [Introduction](#introduction-1)
    - [Operatores](#operators-1)
      - [AddNote](#post-addnote)
      - [DeleteNote](#del-deletenote)
      - [GetAllNotes](#get-getallnotes)
      - [GetNoteDetails](#get-getnotedetails)
      - [Search](#get-search)
      - [EditNote](#patch-editnote)
- [Built With](#built-with-)
- [Project Structure](#project-sructure)
- [License](#license)

## Endpoints
### User endpoint
#### Introduction
In this part you will find all operators you can make such as
* Login With Email And Password
* Register With Email And Password
#### Operators

##### [POST](#endpoints) Login
```
 http://127.0.0.1:9090/user/login
```
**Body** raw (json)
```json
{
  "email": "adelayman0000@gmail.com",
  "password": "123456"
}
```
**Response**
```json
{
  "status": true,
  "data": {
    "userID": "1",
    "userToken": "TOKEN",
    "userName": "Adel Ayman",
    "email": "adelayman0000@gmail.com"
  },
  "message": "Login done successfully"
}
```
---
##### [POST](#endpoints) Register
```
 http://127.0.0.1:9090/user/register
```
**Body** raw (json)
```json
{
  "name": "Adel Ayman",
  "email": "adelayman0000@gmail.com",
  "password": "123456"
}
```
**Response**
```json
{
  "status": true,
  "data": {
    "userID": "2",
    "userToken": "TOKEN",
    "userName": "Adel Ayman",
    "email": "adelayman0000@gmail.com"
  },
  "message": "Registration done successfully"
}
```

### Notes endpoint
#### Introduction
In this part you will find all operators you can make such as
* Save New Note
* Delete a Saved Note
* Update Previously Saved Note
* Get User Notes
* Get Note Details
* Search In Notes

#### Operators

##### [POST](#endpoints) AddNote
```
 http://127.0.0.1:9090/notes
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

**Body** raw (json)
```json
{
  "title": "hello3!!!",
  "description": "Welcome to us again again!",
  "subtitle": "anything",
  "color": 2656,
  "webLink": "https://github.com/adelayman1",
  "image": "https://avatars.githubusercontent.com/u/85571327?s=400&u=b39f4f0b0d503f5826f4d74fa6f951aee05703b6&v=4"
}
```
**Response**
```json
{
  "status": true,
  "data": {
    "userID": "1",
    "email": "TOKEN",
    "userName": "Adel Ayman",
    "email": "adelayman0000@gmail.com"
  },
  "message": "Login done successfully"
}
```
---
##### [DEL](#endpoints) DeleteNote
```
 http://127.0.0.1:9090/notes/{note_id}
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

**Response**
```json
{
  "status": true,
  "data": null,
  "message": "Note has deleted successfully"
}
```
---
##### [GET](#endpoints) GetAllNotes
```
 http://127.0.0.1:9090/notes
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

``` diff
- limit(*optional)
- offset(*optional(starts from 0))
```

**Response**
```json
{
  "status": true,
  "data": [
    {
      "noteId": "1",
      "email": "email",
      "title": "testing note",
      "subtitle": "1sss",
      "description": "postman Descreptionaaaaaaaaankjdkjkjk adel ayman any thing new word",
      "date": "Tuesday, 06 December 2022 14:37",
      "image": "https://avatars.githubusercontent.com/u/85571327?s=400&u=b39f4f0b0d503f5826f4d74fa6f951aee05703b6&v=4",
      "webLink": "https://github.com/adelayman1/QuranAppCoroutine",
      "color": 5
    },.........
  ],
  "message": "Notes have got successfully"
}
```
---
##### [GET](#endpoints) GetNoteDetails
```
 http://127.0.0.1:9090/notes/{note_id}
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

**Response**
```json
{
  "status": true,
  "data": {
    "noteId": "5",
    "email": "EMAIL",
    "title": "details as Lorem ipsum",
    "subtitle": "Lorem ipsum",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Amet commodo nulla facilisi nullam vehicula ipsum a arcu. Viverra mauris in aliquam sem fringilla ut morbi. Nisi vitae suscipit tellus mauris a diam maecenas sed enim. Ornare arcu odio ut sem nulla pharetra diam. Sit amet commodo nulla facilisi nullam vehicula ipsum. Consectetur",
    "date": "Tuesday, 06 December 2022 14:41",
    "image": null,
    "webLink": "https://www.google.com",
    "color": 5
  },
  "message": "Note details has got successfully"
}
```
---
##### [GET](#endpoints) Search
```
 http://127.0.0.1:9090/notes/search
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

**Params**
``` diff
+ search_word(*required)
- limit(*optional)
- offset(*optional(starts from 0))
```
**Response**
```json
{
  "status": true,
  "data": [
    {
      "noteId": "3",
      "email": "EMAIL",
      "title": "testing note",
      "subtitle": "1sss",
      "description": "postman Descreptionaaaaaaaaankjdkjkjk adel ayman any thing new word",
      "date": "Tuesday, 06 December 2022 14:37",
      "image": "https://avatars.githubusercontent.com/u/85571327?s=400&u=b39f4f0b0d503f5826f4d74fa6f951aee05703b6&v=4",
      "webLink": "https://github.com/adelayman1/QuranAppCoroutine",
      "color": 5
    }
  ],
  "message": "Notes have got successfully"
}
```
---
##### [PATCH](#endpoints) EditNote
```
 http://127.0.0.1:9090/notes/{note_id}
```
**Request Headers**

```Authorization```:```Bearer TOKEN```

**Body** raw (json)
```json
{
  "title": "hello3!!!",
  "description": "Welcome to us again again!",
  "subtitle": "anything",
  "color": 2656,
  "webLink": "https://github.com/adelayman1",
  "image": null
}
```
**Response**
```json
{
  "status": true,
  "data": [
    {
      "noteId": "3",
      "email": "EMAIL",
      "title": "testing note",
      "subtitle": "1sss",
      "description": "postman Descreptionaaaaaaaaankjdkjkjk adel ayman any thing new word",
      "date": "Tuesday, 06 December 2022 14:37",
      "image": "https://avatars.githubusercontent.com/u/85571327?s=400&u=b39f4f0b0d503f5826f4d74fa6f951aee05703b6&v=4",
      "webLink": "https://github.com/adelayman1/QuranAppCoroutine",
      "color": 5
    }
  ],
  "message": "Notes have got successfully"
}
```

## Built With 🛠

*  [Kotlin](https://kotlinlang.org/) 
*  [Ktor](https://ktor.io/) 
*  [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html)
*  [Exposed](https://ktor.io/docs/interactive-website-add-persistence.html) 
*  Clean architecture
*  Pagination
*  [JWT Auth](https://ktor.io/docs/jwt.html) 
*  [Koin](https://insert-koin.io/docs/reference/koin-ktor/ktor) 
*  DAO Pattern
*  Repository pattern
*  [jackson](https://github.com/FasterXML/jackson-docs).

## Project Structure
```
📦API
 ┣ 📂data
 ┃ ┣ 📂models
 ┃ ┃ ┣ 📜Notes.kt
 ┃ ┃ ┗ 📜Users.kt
 ┃ ┣ 📂repositories
 ┃ ┃ ┣ 📜NoteRepositoryImpl.kt
 ┃ ┃ ┗ 📜UserRepositoryImpl.kt
 ┃ ┣ 📂source
 ┃ ┃ ┣ 📂dao
 ┃ ┃ ┃ ┣ 📂noteDao
 ┃ ┃ ┃ ┃ ┣ 📜NoteDAO.kt
 ┃ ┃ ┃ ┃ ┗ 📜NoteDAOImpl.kt
 ┃ ┃ ┃ ┗ 📂userDao
 ┃ ┃ ┃ ┃ ┣ 📜UserDAO.kt
 ┃ ┃ ┃ ┃ ┗ 📜UserDAOImpl.kt
 ┃ ┃ ┗ 📜DatabaseFactory.kt
 ┃ ┗ 📂utilities
 ┃ ┃ ┗ 📜UserJWTConfig.kt
 ┣ 📂di
 ┃ ┗ 📂modules
 ┃ ┃ ┗ 📜MainModule.kt
 ┣ 📂domain
 ┃ ┣ 📂models
 ┃ ┃ ┣ 📜BaseResponse.kt
 ┃ ┃ ┣ 📜NoteModel.kt
 ┃ ┃ ┗ 📜UserModel.kt
 ┃ ┣ 📂repositories
 ┃ ┃ ┣ 📜NoteRepository.kt
 ┃ ┃ ┗ 📜UserRepository.kt
 ┃ ┗ 📂usecases
 ┃ ┃ ┣ 📜AddNoteUseCase.kt
 ┃ ┃ ┣ 📜DeleteNoteUseCase.kt
 ┃ ┃ ┣ 📜GetAllUserNotesUseCase.kt
 ┃ ┃ ┣ 📜GetNoteDetailsUseCase.kt
 ┃ ┃ ┣ 📜LoginUseCase.kt
 ┃ ┃ ┣ 📜RegisterUseCase.kt
 ┃ ┃ ┣ 📜SearchNoteUseCase.kt
 ┃ ┃ ┗ 📜UpdateNoteDetailsUseCase.kt
 ┣ 📂plugins
 ┃ ┣ 📜Monitoring.kt
 ┃ ┣ 📜Routing.kt
 ┃ ┣ 📜Security.kt
 ┃ ┗ 📜Serialization.kt
 ┣ 📂routes
 ┃ ┣ 📂note
 ┃ ┃ ┣ 📂requestsModels
 ┃ ┃ ┃ ┣ 📜AddNoteParams.kt
 ┃ ┃ ┃ ┗ 📜UpdateNoteParams.kt
 ┃ ┃ ┗ 📜noteRoute.kt
 ┃ ┗ 📂user
 ┃ ┃ ┣ 📜CreateUserParams.kt
 ┃ ┃ ┣ 📜UserLoginParams.kt
 ┃ ┃ ┗ 📜userRoute.kt
 ┗ 📜Application.kt
```

## LICENSE
```MIT License

Copyright (c) 2022 adelayman1

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.```
