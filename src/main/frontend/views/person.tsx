import {AutoCrud} from "@vaadin/hilla-react-crud";
import {PersonService} from "Frontend/generated/endpoints";
import PersonModel from "Frontend/generated/com/example/exam_chat_bot/entities/PersonModel";

export default function Person() {
    return(
       <AutoCrud service={PersonService} model={PersonModel}/>
    );
}