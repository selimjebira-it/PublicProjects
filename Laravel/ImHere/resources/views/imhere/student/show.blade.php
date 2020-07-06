@extends('layouts.card')
@section('card-title')
    Information About {{$student->name}}
@endsection
@section('card-body')

    <table class="table">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Surname</th>
                <th scope="col">Id</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>{{$student->name}}</td>
                <td>{{$student->surname}}</td>
                <td>{{$student->idStudent}}</td>
            </tr>
        </tbody>

    </table>
    <table class="table">
        <tr>
            <td>
                    <form action="/students/{{$student->id}}/edit" method="get">
                        @csrf
                        <button type="submit" class="btn btn-secondary">edit</button>
                    </form>
            </td>
            <td>
                    <form action="/students" method="get">
                        @csrf
                        <button type="submit" class="btn btn-success">confirm</button>
                    </form>
            </td>
        </tr>

    </table>
    

    
@endsection