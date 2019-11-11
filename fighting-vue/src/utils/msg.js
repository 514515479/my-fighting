import { Message } from "element-ui";

const durationTime = 2000;
const msg = {
    success: (content) => {
        Message.success({
            message: content,
            dangerouslyUseHTMLString: true,
            duration: durationTime,
        })
    },
    info: (content) => {
        Message.info({
            message: content,
            dangerouslyUseHTMLString: true,
            duration: durationTime,
        })
    },
    warning: (content) => {
        Message.warning({
            message: content,
            dangerouslyUseHTMLString: true,
            duration: durationTime,
        })
    },
    error: (content) => {
        Message.error({
            message: content,
            dangerouslyUseHTMLString: true,
            duration: durationTime,
        })
    },
}

export default msg