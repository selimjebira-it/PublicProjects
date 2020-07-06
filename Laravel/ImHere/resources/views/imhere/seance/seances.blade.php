@extends('layouts.card')

@section('card-title')
    Seances
@endsection
@section('card-body')
<table class="table">
    <thead>
        <tr>
            <th scope="col">Slug</th>
            <th scope="col">Seance n°</th> 
            <th scope="col">Local </th> 
            <th scope="col">Date</th>
            <th scope="col">Hour</th>
            <th scope="col">Duration</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
            

        </tr>
    </thead>
    <tbody>
            @foreach ($seances as $seance)
                <tr>
                    <th scope="row">{{$seance->slug}}</th>
                    <td>
                        {{$seance->seanceNb}}
                    </td>
                    <td>
                        {{$seance->local}}
                    </td>
                    <td>
                        {{$seance->date}}
                    </td>
                    <td>
                        {{$seance->hour}}
                    </td>
                    <td>
                        {{$seance->duration}}
                    </td>

                    <td>
                            <form action="/seances/{{$seance->id}}" method="GET">
                                @csrf
                                <button type = "submit" class="btn btn-success">Manage Presence</button>
                            </form>
                    </td>


                    <td>
                            <form action="/seances/{{$seance->id}}/edit" method="GET">
                                @csrf
                                <button type = "submit" class="btn btn-secondary">Edit</button>
                            </form>
                    </td>
                    <td>
                        <form action="/seances/{{$seance->id}}" method="POST">
                           
                            @method("DELETE")
                            @csrf
                                <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            @endforeach

    </tbody>
</table>
<form action="/seances/create">
    @csrf
    <button type= "submit" class = "btn btn-primary">Add Seance</button>
</form>

@endsection

            


