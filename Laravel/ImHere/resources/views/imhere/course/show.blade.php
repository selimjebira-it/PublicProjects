@extends('layouts.card')
@section('card-title')
    Information About {{$course->name}}
@endsection
@section('card-body')

    <table class="table">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Slug</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>{{$course->name}}</td>
                <td>{{$course->slug}}</td>
            </tr>
        </tbody>

    </table>
    <table class="table">
        <tr>
            <td>
                    <form action="/courses/{{$course->id}}/edit" method="get">
                        @csrf
                        <button type="submit" class="btn btn-secondary">edit</button>
                    </form>
            </td>
            <td>
                    <form action="/courses" method="get">
                        @csrf
                        <button type="submit" class="btn btn-success">confirm</button>
                    </form>
            </td>
        </tr>

    </table>
    

    
@endsection