@extends('layouts.card')

@section('card-title')
    Presences
@endsection
@section('card-body')
    <table class="table">
        <thead>
            <tr>
                <td>
                    Name
                </td>
                <td>
                    SurName
                </td>
                <td>
                    Course
                </td>
                <td>
                    N° Seance
                </td>
            </tr>
        </thead>
        <tbody>
            
            @foreach ($presences as $presence)
            <tr>
                <td>
                    {{$presence->name}}
                </td>
                <td>
                    {{$presence->surname}}
                </td>
                <td>
                    {{$presence->slug}}
                </td>
                <td>
                    {{$presence->seanceNb}}
                </td>

            </tr>
            @endforeach
        </tbody>


    </table>

    



@endsection