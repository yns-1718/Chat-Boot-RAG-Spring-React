import {Button, TextField} from "@vaadin/react-components";
import {useState} from "react";
import {ChatAiService} from "Frontend/generated/endpoints";
import Markdown from "react-markdown";

export default function Chat(){
    const[question,setQuestion]=useState<string>("")
    const[response,setResponse] =useState< string | undefined>("")

    async function send()
    {

        ChatAiService.ragChat(question).then(resp=>{
            setResponse(resp)
        })
    }


    return(
        <div className="p-m">
            <h3> Chat Bot </h3>
            <div>
                <TextField style={{width:'80%'}}
                           onChange={(e=>setQuestion(e.target.value))}></TextField>
                <Button theme="primary" onClick={send}>Envoyer</Button>
                <div>
                    <Markdown>
                       {response}
                    </Markdown>
                </div>
            </div>
        </div>
    )
 }