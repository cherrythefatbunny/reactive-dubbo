var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "90000",
        "ok": "68555",
        "ko": "21445"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "102",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "10372",
        "ok": "8493",
        "ko": "10372"
    },
    "meanResponseTime": {
        "total": "557",
        "ok": "275",
        "ko": "1459"
    },
    "standardDeviation": {
        "total": "1738",
        "ok": "745",
        "ko": "3136"
    },
    "percentiles1": {
        "total": "118",
        "ok": "116",
        "ko": "210"
    },
    "percentiles2": {
        "total": "284",
        "ok": "219",
        "ko": "578"
    },
    "percentiles3": {
        "total": "1268",
        "ok": "639",
        "ko": "10006"
    },
    "percentiles4": {
        "total": "10010",
        "ok": "2844",
        "ko": "10125"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 66479,
        "percentage": 74
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 895,
        "percentage": 1
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 1181,
        "percentage": 1
    },
    "group4": {
        "name": "failed",
        "count": 21445,
        "percentage": 24
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "803.571",
        "ok": "612.098",
        "ko": "191.473"
    }
},
contents: {
"req_async-0df93": {
        type: "REQUEST",
        name: "async",
path: "async",
pathFormatted: "req_async-0df93",
stats: {
    "name": "async",
    "numberOfRequests": {
        "total": "90000",
        "ok": "68555",
        "ko": "21445"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "102",
        "ko": "2"
    },
    "maxResponseTime": {
        "total": "10372",
        "ok": "8493",
        "ko": "10372"
    },
    "meanResponseTime": {
        "total": "557",
        "ok": "275",
        "ko": "1459"
    },
    "standardDeviation": {
        "total": "1738",
        "ok": "745",
        "ko": "3136"
    },
    "percentiles1": {
        "total": "118",
        "ok": "116",
        "ko": "210"
    },
    "percentiles2": {
        "total": "284",
        "ok": "219",
        "ko": "578"
    },
    "percentiles3": {
        "total": "1268",
        "ok": "639",
        "ko": "10006"
    },
    "percentiles4": {
        "total": "10010",
        "ok": "2844",
        "ko": "10125"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 66479,
        "percentage": 74
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 895,
        "percentage": 1
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 1181,
        "percentage": 1
    },
    "group4": {
        "name": "failed",
        "count": 21445,
        "percentage": 24
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "803.571",
        "ok": "612.098",
        "ko": "191.473"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
