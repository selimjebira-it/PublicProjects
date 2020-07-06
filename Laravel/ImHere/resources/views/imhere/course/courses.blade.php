@extends('layouts.card')
@section('card-title')
    Courses
@endsection
@section('card-body')
<table class="table">
    <thead>
        <tr>
            
            <th scope="col">Name</th>
            <th scope="col">Slug</th> 
            <th scope="col"></th>
            <th scope="col"></th>

        </tr>
    </thead>
    <tbody>
            @foreach ($courses as $course)
                <tr>
                    <td>{{$course->name}}</td>
                    <td>{{$course->slug}}</td>
                    <td>
                            <form action="/courses/{{$course->id}}/edit" method="GET">
                                @csrf
                                <button type = "submit" class="btn btn-secondary">Edit</button>
                            </form>
                    </td>
                    <td>
                        <form action="/courses/{{$course->id}}" method="POST">
                           
                            @method("DELETE")
                            @csrf
                                <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach

    </tbody>
</table>
<form action="/courses/create">
    @csrf
    <button type= "submit" class = "btn btn-primary">Add Course</button>
</form>

@endsection

            


