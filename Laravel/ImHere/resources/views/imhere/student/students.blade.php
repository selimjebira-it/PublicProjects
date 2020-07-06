@extends('layouts.card')

@section('card-title')
    Students
@endsection
@section('card-body')
<table class="table">
    <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">LastName</th> 
            <th scope="col"></th>
            <th scope="col"></th>

        </tr>
    </thead>
    <tbody>
            @foreach ($students as $student)
                <tr>
                    <th scope="row">{{$student->studentId}}</th>
                    <td>{{$student->name}}</td>
                    <td>{{$student->surname}}</td>
                    <td>
                            <form action="/students/{{$student->id}}/edit" method="GET">
                                @csrf
                                <button type = "submit" class="btn btn-secondary">Edit</button>
                            </form>
                    </td>
                    <td>
                        <form action="/students/{{$student->id}}" method="POST">
                           
                            @method("DELETE")
                            @csrf
                                <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach

    </tbody>
</table>
<form action="/students/create">
    @csrf
    <button type= "submit" class = "btn btn-primary">Add Student</button>
</form>

@endsection

            


